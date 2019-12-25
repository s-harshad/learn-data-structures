package coding;

public class Tire<VALUE> {

  private static final int R = 256; // Extended ASCII
  private Node root; // root of trie
  private int n; // number of keys in trie

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table and {@code
   *     null} if the key is not in the symbol table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public VALUE get(String key) {
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    Node x = get(root, key, 0);
    if (x == null) return null;
    return (VALUE) x.val;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) return x;
    char c = key.charAt(d);
    return get(x.next[c], key, d + 1);
  }

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table. If the value is {@code null}, this effectively
   * deletes the key from the symbol table.
   *
   * @param key the key
   * @param val the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public void put(String key, VALUE val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");
    if (val == null) delete(key);
    else root = put(root, key, val, 0);
  }

  private Node put(Node x, String key, VALUE val, int d) {
    if (x == null) x = new Node();
    if (d == key.length()) {
      if (x.val == null) n++;
      x.val = val;
      return x;
    }
    char c = key.charAt(d);
    x.next[c] = put(x.next[c], key, val, d + 1);
    return x;
  }

  /**
   * Removes the key from the set if the key is present.
   *
   * @param key the key
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public void delete(String key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    root = delete(root, key, 0);
  }

  private Node delete(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) {
      if (x.val != null) n--;
      x.val = null;
    } else {
      char c = key.charAt(d);
      x.next[c] = delete(x.next[c], key, d + 1);
    }

    // remove subtrie rooted at x if it is completely empty
    if (x.val != null) return x;
    for (int c = 0; c < R; c++) if (x.next[c] != null) return x;
    return null;
  }

  /**
   * Returns the number of key-value pairs in this symbol table.
   *
   * @return the number of key-value pairs in this symbol table
   */
  public int size() {
    return n;
  }

  /**
   * Is this symbol table empty?
   *
   * @return {@code true} if this symbol table is empty and {@code false} otherwise
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  private static class Node {
    private Object val;
    private Node[] next = new Node[R];
  }
}
