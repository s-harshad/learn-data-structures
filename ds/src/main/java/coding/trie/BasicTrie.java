package coding.trie;

import java.util.ArrayDeque;
import java.util.Queue;

public class BasicTrie<T> extends Trie<T> {

  private static final int R = 256; // Extended ASCII

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table. If the value is {@code null}, this effectively
   * deletes the key from the symbol table.
   *
   * @param key the key
   * @param value the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  @Override
  public void put(String key, T value) {
    validateKey(key);
    root = retrieveExistingOrCreateNewNode(root);

    // start from root
    Node<T> node = root;

    // for each character, create/append nodes to existing chain
    for (int i = 0; i < key.length(); i++) {
      char c = characterAtIndex(key, i);
      Node n1 = retrieveExistingOrCreateNewNode(node.getChild(c));
      node.setChild(c, n1);
      node = n1;
    }

    if (node.val == null) n++; // note the count
    node.val = value; // insert the value
  }

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table and {@code
   *     null} if the key is not in the symbol table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  @Override
  public T get(String key) {
    Node<T> node = getNode(key);
    return node == null ? null : node.val;
  }

  /**
   * Traverse till the Node the represents the given {@code key}
   *
   * @param key key to traverse
   * @return Node represents the given {@code key} if preset; {@code null} otherwise
   */
  private final Node getNode(String key) {
    validateKey(key);

    Node<T> node = root;
    for (int i = 0; i < key.length(); i++) {
      if (node == null) return null;
      char c = characterAtIndex(key, i);
      node = node.getChild(c);
    }

    return node;
  }

  /**
   * Removes the key from the set if the key is present.
   *
   * @param key the key
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  @Override
  public void delete(String key) {
    validateKey(key);

    // data structure to keep intermediate nodes, as we traverse
    // these are used to remove subtrie's if they are completely empty.
    Node<T>[] nodes = new Node[key.length() + 1];
    nodes[0] = root;

    // start from root
    Node<T> node = root;
    for (int i = 0; i < key.length(); i++) {
      if (node == null) return;
      char c = characterAtIndex(key, i);
      nodes[i + 1] = node = node.getChild(c);
    }

    if (node == null) return;
    if (node.val != null) n--; // only decrement count if we are deleting an actual value.
    node.val = null; // remove the value

    // remove subtries, if it is completely empty
    for (int i = nodes.length - 1; i > 0; i--) {
      if (isNodeCompletelyEmpty(nodes[i])) {
        Node<T> prev = nodes[i - 1];
        char c = characterAtIndex(key, i - 1);
        prev.setChild(c, null);
      } else {
        return;
      }
    }

    if (isNodeCompletelyEmpty(root)) root = null;
  }

  /**
   * Returns all keys in the symbol table as an {@code Iterable}. To iterate over all of the keys in
   * the symbol table named {@code st}, use the foreach notation: {@code for (Key key : st.keys())}.
   *
   * @return all keys in the symbol table as an {@code Iterable}
   */
  @Override
  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  /**
   * Returns all of the keys in the set that start with {@code prefix}.
   *
   * @param prefix the prefix
   * @return all of the keys in the set that start with {@code prefix}, as an iterable
   */
  public Iterable<String> keysWithPrefix(String prefix) {
    Queue<String> results = new ArrayDeque<>();
    Node x = getNode(prefix);
    collect(x, new StringBuilder(prefix), results);
    return results;
  }

  /**
   * Recursive function to collect all keys in queue
   *
   * @param currentNode current node
   * @param prefix current prefix
   * @param results results.
   */
  private void collect(Node<T> currentNode, StringBuilder prefix, Queue<String> results) {
    if (currentNode == null) return; // exit condition
    if (currentNode.val != null) results.add(prefix.toString());
    for (char c = 0; c < R; c++) {
      prefix.append(c);
      collect(currentNode.getChild(c), prefix, results);
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }

  /**
   * Returns the string in the symbol table that is the longest prefix of {@code query}, or {@code
   * null}, if no such string.
   *
   * @param query the query string
   * @return the string in the symbol table that is the longest prefix of {@code query}, or {@code
   *     null} if no such string
   * @throws IllegalArgumentException if {@code query} is {@code null}
   */
  public String longestPrefixOf(String query) {
    if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
    int length = longestPrefixOf(root, query, 0, -1);
    if (length == -1) return null;
    else return query.substring(0, length);
  }

  // returns the length of the longest string key in the subtrie
  // rooted at x that is a prefix of the query string,
  // assuming the first d character match and we have already
  // found a prefix match of given length (-1 if no such match)
  private int longestPrefixOf(Node x, String query, int d, int length) {
    if (x == null) return length;
    if (x.val != null) length = d;
    if (d == query.length()) return length;
    char c = query.charAt(d);
    return longestPrefixOf(x.getChild(c), query, d + 1, length);
  }

  /**
   * Create new node or return existing one if it exists.
   *
   * @param n node
   * @return new node or existing one if it exists.
   */
  protected final Node<T> retrieveExistingOrCreateNewNode(Node<T> n) {
    return n == null ? new TrieNode<T>() : n;
  }

  /**
   * For the given String {@code str}, and index postion {@code i} return the character at that
   * positon.
   *
   * @param str string
   * @param i position
   * @return the character at that positon {@code i}.
   */
  private final char characterAtIndex(String str, int i) {
    return str.charAt(i);
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
    for (char r = 0; r < R; r++) if (n.getChild(r) != null) return false;
    return true;
  }

  static final class TrieNode<T> extends Node<T> {
    Node<T>[] next = new TrieNode[R]; // extended ascii

    @Override
    Node getChild(int c) {
      return this.next[c];
    }

    @Override
    void setChild(int c, Node n) {
      this.next[c] = n;
    }
  }
}
