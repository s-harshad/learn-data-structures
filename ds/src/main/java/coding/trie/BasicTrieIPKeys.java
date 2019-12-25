package coding.trie;

/**
 * Trie Data Structure where keys are IP Addresses.
 *
 * @param <T>
 */
public class BasicTrieIPKeys<T> extends BasicTrie<T> {

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table. If the value is {@code null}, this effectively
   * deletes the key from the symbol table.
   *
   * @param key ip-address. Format x.x.x.x
   * @param value the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  @Override
  public final void put(String key, T value) {
    validateKey(key);
    root = retrieveExistingOrCreateNewNode(root);
    Node<T> node = root;     // start from root

    String[] keyParts = key.split("\\.");
    // for each keyPart, create/append nodes to existing chain
    for (int i = 0; i < keyParts.length; i++) {
      int c = Integer.valueOf(keyParts[i]);
      validate(c);
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
   * @param key ip-address
   * @return the value associated with the given key if the key is in the symbol table and {@code
   *     null} if the key is not in the symbol table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  @Override
  public final T get(String key) {
    Node<T> node = getNode(key);
    if (node == null) return null;
    return node.val;
  }

  public void delete(String key) {
    throw new UnsupportedOperationException("This will be implemented later.");
  }

  public Iterable<String> keys() {
    throw new UnsupportedOperationException("This will be implemented later.");
  }

  public Iterable<String> keysWithPrefix(String prefix) {
    throw new UnsupportedOperationException("This will be implemented later.");
  }

  public String longestPrefixOf(String query) {
    throw new UnsupportedOperationException("This will be implemented later.");
  }

  /**
   * Traverse till the Node the represents the given {@code key}
   *
   * @param key key to traverse
   * @return Node represents the given {@code key} if preset; {@code null} otherwise
   */
  private final Node getNode(String key) {
    String[] keyParts = key.split("\\.");
    Node<T> node = root;
    for (int i = 0; i < keyParts.length; i++) {
      validateKey(keyParts[i]);
      if (node == null) return null;
      int c = Integer.valueOf(keyParts[i]);
      node = node.getChild(c);
    }
    return node;
  }

  private void validate(Integer x) {
    if (x < 0 || x > 256)
      throw new IllegalArgumentException(String.format("Value %d is not between 0 and 255", x));
  }
}
