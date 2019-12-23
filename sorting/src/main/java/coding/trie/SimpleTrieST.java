package coding.trie;

/**
 * Implementation of Trie Data Structure as a symbol table..
 *
 * @param <T> generic. indicates the type of value to store. keys are string.
 * @author Harshad Shrishrimal
 */
public final class SimpleTrieST<T> {

  private static final int R = 256; // Extended ASCII

  private Node<T> root; // root of trie
  private int n; // number of keys in trie

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table and {@code
   *     null} if the key is not in the symbol table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public final T get(String key) {
    validateKey(key);

    Node<T> node = root;
    for (int i = 0; i < key.length(); i++) {
      if (node == null) return null;
      int c = characterAtIndex(key, i);
      node = node.next[c];
    }

    if (node == null) return null;
    return node.val;
  }

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table. If the value is {@code null}, this effectively
   * deletes the key from the symbol table.
   *
   * @param key the key
   * @param value the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public final void put(String key, T value) {

    validateKey(key);

    root = retrieveExistingOrCreateNewNode(root);

    // start from root
    Node<T> node = root;

    // for each character, create/append nodes to existing chain
    for (int i = 0; i < key.length(); i++) {
      int c = characterAtIndex(key, i);
      node = node.next[c] = retrieveExistingOrCreateNewNode(node.next[c]);
    }

    if (node.val == null) n++; // note the count
    node.val = value; // insert the value
  }

  /**
   * Removes the key from the set if the key is present.
   *
   * @param key the key
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public final void delete(String key) {
    validateKey(key);

    // data structure to keep intermediate nodes, as we traverse
    // these are used to remove subtrie's if they are completely empty.
    Node<T>[] nodes = new Node[key.length() + 1];
    nodes[0] = root;

    // start from root
    Node<T> node = root;
    for (int i = 0; i < key.length(); i++) {
      if (node == null) return;
      int c = characterAtIndex(key, i);
      nodes[i + 1] = node = node.next[c];
    }

    if (node == null) return;
    if (node.val != null) n--; // only decrement count if we are deleting an actual value.
    node.val = null; // remove the value

    // remove subtries, if it is completely empty
    for (int i = nodes.length - 1; i > 0; i--) {
      if (isNodeCompletelyEmpty(nodes[i])) {
        Node<T> prev = nodes[i - 1];
        int c = characterAtIndex(key, i - 1);
        prev.next[c] = null;
      } else {
        return;
      }
    }

    if (isNodeCompletelyEmpty(root)) root = null;
  }

  /**
   * Does this symbol table contain the given key?
   *
   * @param key the key
   * @return {@code true} if this symbol table contains {@code key} and {@code false} otherwise
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public final boolean contains(String key) {
    validateKey(key);
    return get(key) != null;
  }

  /**
   * Returns the number of key-value pairs in this symbol table.
   *
   * @return the number of key-value pairs in this symbol table
   */
  public final int size() {
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

  /**
   * Make sure {@code key} is valid. At present checking for not null.
   *
   * @param key String to validate
   */
  private final void validateKey(String key) {
    if (key == null) throw new IllegalArgumentException("Key is null");
  }

  /**
   * For the given String {@code str}, and index postion {@code i} return the character at that
   * positon.
   *
   * @param str string
   * @param i position
   * @return the character at that positon {@code i}.
   */
  private final Integer characterAtIndex(String str, int i) {
    return str.charAt(i) - 'a';
  }

  /**
   * For the given Node {@code n}, check if it's completely empty. it's empty if it's {@code n.val}
   * is null and it's collection empty.
   *
   * @param n node to verify
   * @return {@code true} if node is empty/null; {@code false} otherwise.
   */
  private final boolean isNodeCompletelyEmpty(Node n) {

    if (n.val != null) return false;

    for (char r = 0; r < R; r++) if (n.next[r] != null) return false;

    return true;
  }

  /**
   * Create new node or return existing one if it exists.
   *
   * @param n node
   * @return new node or existing one if it exists.
   */
  private final Node<T> retrieveExistingOrCreateNewNode(Node<T> n) {
    if (n == null) return new Node<T>();
    return n;
  }
}
