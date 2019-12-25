package coding.trie;

public abstract class Trie<T> {

  protected Node<T> root; // root of trie
  protected int n; // number of keys in trie

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table. If the value is {@code null}, this effectively
   * deletes the key from the symbol table.
   *
   * @param key the key
   * @param value the value
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public abstract void put(String key, T value);

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table and {@code
   *     null} if the key is not in the symbol table
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public abstract T get(String key);

  /**
   * Removes the key from the set if the key is present.
   *
   * @param key the key
   * @throws IllegalArgumentException if {@code key} is {@code null}
   */
  public abstract void delete(String key);

  /**
   * Returns all keys in the symbol table as an {@code Iterable}. To iterate over all of the keys in
   * the symbol table named {@code st}, use the foreach notation: {@code for (Key key : st.keys())}.
   *
   * @return all keys in the symbol table as an {@code Iterable}
   */
  public abstract Iterable<String> keys();

  /**
   * Returns all of the keys in the set that start with {@code prefix}.
   *
   * @param prefix the prefix
   * @return all of the keys in the set that start with {@code prefix}, as an iterable
   */
  public abstract Iterable<String> keysWithPrefix(String prefix);

  /**
   * Returns the string in the symbol table that is the longest prefix of {@code query}, or {@code
   * null}, if no such string.
   *
   * @param query the query string
   * @return the string in the symbol table that is the longest prefix of {@code query}, or {@code
   *     null} if no such string
   * @throws IllegalArgumentException if {@code query} is {@code null}
   */
  public abstract String longestPrefixOf(String query);

  /**
   * Returns the number of key-value pairs in this symbol table.
   *
   * @return the number of key-value pairs in this symbol table
   */
  public final int size() {
    return n;
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
   * Is this symbol table empty?
   *
   * @return {@code true} if this symbol table is empty and {@code false} otherwise
   */
  public final boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Make sure {@code key} is valid. At present checking for not null.
   *
   * @param key String to validate
   */
  final void validateKey(String key) {
    if (key == null) throw new IllegalArgumentException("Key is null");
  }
}
