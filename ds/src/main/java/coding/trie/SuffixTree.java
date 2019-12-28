package coding.trie;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final public class SuffixTree<T> extends Trie<T> {

  private final Trie<Set<String>> radixTree;
  private final Map<String, T> valueMap;

  public SuffixTree() {
    this.radixTree = new RadixTrie<Set<String>>();
    this.valueMap = new TreeMap<>();
  }

  @Override
  public void put(String key, T value) {
    // TODO: Add validation
    validateKey(key);

    // Put/replace value in map before we add suffixes to the tree
    T replacedValue = valueMap.put(key, value);

    // We only need to modify the tree if we have not added this key before...
    if (replacedValue == null) {
      addSuffixesToRadixTree(key);
    }

    return;
  }

  /**
   * For the given {@code key} generate all suffixes' and add it to RadixTree
   *
   * @param key key whose suffixes' will be added to Radix Trie.
   */
  private void addSuffixesToRadixTree(String key) {
    int N = key.length();
    for (int i = N - 1; i >= 0; i--) {
      String suffix = key.substring(i, N);
      Set<String> originalKeyRefs = this.radixTree.get(suffix);
      if (originalKeyRefs == null) {
        originalKeyRefs = Collections.newSetFromMap(new HashMap<String, Boolean>());
        radixTree.put(suffix, originalKeyRefs);
      }
      originalKeyRefs.add(key);
    }
  }

  /**
   * For the given {@code key} generate all suffixes' and remove it from RadixTree
   *
   * @param key key whose suffixes' will be removed from Radix Trie.
   */
  private void addSuffixesFromRadixTree(String key) {
    int N = key.length();
    for (int i = N - 1; i >= 0; i--) {
      String suffix = key.substring(i, N);
      Set<String> originalKeyRefs = radixTree.get(suffix);
      originalKeyRefs.remove(key);
      // We just removed the last original key which shares this suffix.
      // Remove the suffix from the tree entirely...
      if (originalKeyRefs.isEmpty()) radixTree.delete(suffix);
      // else leave the suffix in the tree, as it is a common suffix of another key.
    }
  }

  @Override
  public T get(String key) {
    return valueMap.get(key);
  }

  @Override
  public void delete(String key) {
    validateKey(key);

    if (valueMap.get(key) == null) // Key was not stored, no need to do anything, return ...
    return;

    addSuffixesFromRadixTree(key);
    valueMap.remove(key);
  }

  @Override
  public Iterable<String> keys() {
    return valueMap.keySet();
  }

  @Override
  public Iterable<String> keysWithPrefix(String prefix) {
    Iterable<String> iterable = radixTree.keysWithPrefix(prefix);
    Set<String> keys =
        StreamSupport.stream(iterable.spliterator(), false)
            .map(key -> this.radixTree.get(key))
            .flatMap(x -> x.stream())
            .collect(Collectors.toSet());

    return keys;
  }

  @Override
  public String longestPrefixOf(String query) {
    throw new UnsupportedOperationException();
  }

  /**
   * Pretty print the tree Mostly used for unit testing.
   *
   * @param sb contains the pretty printed tree.
   */
  public void prettyPrint(Appendable sb) {
    RadixTrie t = (RadixTrie) radixTree;
    t.prettyPrint(sb);
  }
}
