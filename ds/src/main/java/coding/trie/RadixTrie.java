package coding.trie;

import coding.trie.RadixTrie.SearchResult.Classification;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of Radix Trie algorithm.
 *
 * @param <T> generic value for symbol table.
 * @author Harshad Shrishrimal
 */
public class RadixTrie<T> extends Trie<T> {

  RadixNode<T> root = new RadixNode<>();

  public int largestPrefixLength(CharSequence a, CharSequence b) {
    int len = 0;
    int to = Math.min(a.length(), b.length());
    for (; len < to && a.charAt(len) == b.charAt(len); len++) {}
    return len;
  }

  /**
   * Pretty print the tree
   * Mostly used for unit testing.
   *
   * @param sb contains the pretty printed tree.
   */
  public void prettyPrint(Appendable sb) {
    prettyPrint(root, sb, "", true, true);
  }

  /**
   * Recursively pretty print the tree.
   *
   * @param node current node
   * @param sb contains the pretty printed tree
   * @param prefix already pretty printed prefix
   * @param isTail flags
   * @param isRoot flags
   */
  private void prettyPrint(
      RadixNode node, Appendable sb, String prefix, boolean isTail, boolean isRoot) {
    try {
      StringBuilder label = new StringBuilder();
      if (isRoot) label.append("○");
      label.append(node.prefix);
      if (node.value != null) {
        label.append(" (").append(node.value).append(")");
      }
      sb.append(prefix)
          .append(isTail ? isRoot ? "" : "└── ○ " : "├── ○ ")
          .append(label)
          .append("\n");
      List<RadixNode> children = new ArrayList<>(node.outGoingNodes);
      for (int i = 0; i < children.size() - 1; i++) {
        prettyPrint(
            children.get(i), sb, prefix + (isTail ? isRoot ? "" : "    " : "│   "), false, false);
      }
      if (!children.isEmpty()) {
        prettyPrint(
            children.get(children.size() - 1),
            sb,
            prefix + (isTail ? isRoot ? "" : "    " : "│   "),
            true,
            false);
      }
    } catch (IOException ioException) {
      // Rethrow the checked exception as a runtime exception...
      throw new IllegalStateException(ioException);
    }
  }

  @Override
  public void put(String key, T value) {
    validateKey(key);
    put(key, value, root);
  }

  /**
   * Inserts the key-value pair into the symbol table, overwriting the old value with the new value
   * if the key is already in the symbol table.
   *
   * When attempting to insert there are 4 possibilities.
   *
   * 1. key is NOT MATCHING with the current value in the node.
   *    In this case, we just add the key as a child of the node.
   *
   * 2. key MATCHES completely with what's the current value in the node.
   *    In this case, we update the node's value, replacing old value if present
   *
   * 3. key MATCHES with the current value in the node, but key is larger than what's @ node
   *    In this case, we ignore the matched portion and recursively call the same function again.
   *    Usually in the next call, it goes in case 1.
   *
   * 4. key MATCHES partially with what is at the node.
   *    In this case, we split the node, and recursively call the same function again.
   *    In the next call, it will be case 3.
   *
   * @param key key to insert
   * @param value value to insert
   * @param node current node
   */
  private void put(CharSequence key, T value, RadixNode<T> node) {

    SearchResult searchResult = searchKey(key, node);
    SearchResult.Classification classification = searchResult.classification;

    switch (classification) {
      case NO_MATCH: // add the key/value pair as a child of this node
        n++;
        node.outGoingNodes.add(createNode(key, value));
        return;
      case EXACT_MATCH_WITH_PREFIX: // update or add new
        if (!searchResult.node.hasValue) n++;
        searchResult.node.value = value;
        searchResult.node.hasValue = true;
        return;
      case EXACT_MATCH_WITH_PREFIX_KEY_LARGER_THAN_PREFIX:
        put(searchResult.keySuffix, value, searchResult.node);
        return;
      case PARTIAL_MATCH:
        /*  split the node
        existing node prefix is going to be the matched sequence.
        newNode prefix will be the remaining part of the prefix.
        newNode's children/outgoing edges will be exisiting node's children */
        RadixNode newNode = createNode(searchResult.prefixSuffix, searchResult.node.value);
        newNode.hasValue = searchResult.node.hasValue;
        newNode.outGoingNodes = searchResult.node.outGoingNodes;
        searchResult.node.prefix = searchResult.matchedSequence;
        searchResult.node.value = null;
        searchResult.node.hasValue = false;
        searchResult.node.outGoingNodes = new TreeSet();
        searchResult.node.outGoingNodes.add(newNode);
        put(key, value, node);
        return;
    }
  }

  @Override
  public T get(String key) {
    validateKey(key);
    RadixNode<T> node = get(key, root);
    return node == null ? null : node.hasValue ? node.value : null;
  }

  private RadixNode<T> get(CharSequence prefix, RadixNode<T> node) {
    if (prefix.length() == 0) return node; // exit condition

    SearchResult searchResult = searchKey(prefix, node);
    SearchResult.Classification classification = searchResult.classification;

    switch (classification) {
      case NO_MATCH:
        return null;
      default:
        return get(searchResult.keySuffix, searchResult.node);
    }
  }

  @Override
  public void delete(String key) {
    validateKey(key);
    delete(key, root);
  }

  private void delete(CharSequence prefix, RadixNode node) {

    SearchResult searchResult = searchKey(prefix, node);

    if (Classification.NO_MATCH == searchResult.classification) {
      return; // Nothing to do.
    }

    if (Classification.EXACT_MATCH_WITH_PREFIX == searchResult.classification) {
      // we found the last node
      // check if this node has any children. if yes then we cannot remove this node
      if (!searchResult.node.outGoingNodes.isEmpty()) {
        searchResult.node.value = null;
        searchResult.node.hasValue = false;
        n--;
        // check how many children does this node have.
        // if only 1 then we can merge it with it's parent.
        mergeIfApplicable(searchResult);
        return;
      }

      // leaf node. remove it
      if (searchResult.node.outGoingNodes.isEmpty()) {
        searchResult.nodeParent.outGoingNodes.remove(searchResult.node);
      }
    }

    delete(searchResult.keySuffix, searchResult.node);

    // check how many children does this node have.
    // if only 1 then we can merge it with it's parent.
    mergeIfApplicable(searchResult);

    return;
  }

  /**
   * If a node has only 1 children, and the node doesn't have any value then
   * we can merge the child with the node (combine the prefixes).
   * Also, re-arrange the children of the merging node, as they now become the children
   * of merged node.
   *
   * @param searchResult {@code searchResult.node} contains the node that will be merged, if applicable
   */
  private void mergeIfApplicable(SearchResult searchResult) {
    if (searchResult.node.outGoingNodes.size() == 1 && searchResult.node.hasValue == false) {
      RadixNode n = (RadixNode) searchResult.node.outGoingNodes.iterator().next();
      searchResult.node.prefix = searchResult.node.prefix.toString() + n.prefix.toString();
      searchResult.node.hasValue = n.hasValue;
      searchResult.node.value = n.value;
      searchResult.node.outGoingNodes = n.outGoingNodes;
      n.outGoingNodes = new TreeSet();
    }
  }

  @Override
  public Iterable<String> keys() {
    Queue<String> results = new ArrayDeque<>();
    RadixNode node = get("", root);
    collect(node, new StringBuilder(), results);
    return results;
  }

  @Override
  public Iterable<String> keysWithPrefix(String prefix) {
    return keysWithPrefix(prefix, root, new ArrayDeque<>(), new StringBuilder());
  }

  private Iterable<String> keysWithPrefix(
      CharSequence prefix, RadixNode node, Queue<String> results, StringBuilder pb) {
    SearchResult searchResult = searchKey(prefix, node);
    switch (searchResult.classification) {
      case NO_MATCH:
        return results;
      case EXACT_MATCH_WITH_PREFIX_KEY_LARGER_THAN_PREFIX:
        pb.append(searchResult.matchedSequence);
        keysWithPrefix(searchResult.keySuffix, searchResult.node, results, pb);
        break;
      case EXACT_MATCH_WITH_PREFIX:
      case PARTIAL_MATCH:
        collect(searchResult.node, pb, results);
        break;
    }
    return results;
  }

  private void collect(RadixNode<T> node, StringBuilder prefix, Queue<String> results) {
    if (node == null) return;
    prefix.append(node.prefix);
    if (node.hasValue) results.add(prefix.toString());
    Set<RadixNode<T>> edges = node.outGoingNodes;
    for (RadixNode<T> edge : edges) {
      collect(edge, prefix, results);
      prefix.delete(prefix.lastIndexOf(((String) edge.prefix)), prefix.length());
    }
  }

  @Override
  public String longestPrefixOf(String query) {
    if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
    return longestPrefixOf(query, root, new StringBuilder(), "");
  }

  private String longestPrefixOf(
      CharSequence query, RadixNode node, StringBuilder prefixBuilder, CharSequence result) {

    SearchResult searchResult = searchKey(query, node);
    switch (searchResult.classification) {
      case NO_MATCH:
      case PARTIAL_MATCH:
        break;
      case EXACT_MATCH_WITH_PREFIX_KEY_LARGER_THAN_PREFIX:
        prefixBuilder.append(searchResult.matchedSequence);
        if (searchResult.node.hasValue) result = prefixBuilder.toString();
        result = longestPrefixOf(searchResult.keySuffix, searchResult.node, prefixBuilder, result);
        break;
      case EXACT_MATCH_WITH_PREFIX:
        prefixBuilder.append(searchResult.matchedSequence);
        if (searchResult.node.hasValue) result = prefixBuilder.toString();
        break;
    }

    return result.toString();
  }

  private RadixNode createNode(CharSequence prefix, Object value) {
    RadixNode n = new RadixNode();
    n.prefix = prefix;
    n.hasValue = true;
    n.value = value;
    n.outGoingNodes = new TreeSet();
    return n;
  }

  private SearchResult searchKey(CharSequence key, RadixNode node) {
    if (node == null) return new SearchResult(null, null, -1, key);
    Set<RadixNode> edges = node.outGoingNodes;
    for (RadixNode n : edges) {
      int charsMatched = largestPrefixLength(key, n.prefix);
      if (charsMatched > 0) return new SearchResult(node, n, charsMatched, key);
    }
    return new SearchResult(null, null, -1, key);
  }

  /**
   * Class encapsulating the node, matched sequence and any remaining strings.
   */
  protected static class SearchResult {

    RadixNode nodeParent; // parent of node node.
    RadixNode node; // node on with comparsion is performec
    CharSequence keySuffix; // contains non-matched portion of the key, we need to insert/delete or get on.
    CharSequence prefixSuffix; // contains non-matched portion of node's prefix.
    CharSequence matchedSequence; // contains matched portion
    CharSequence searchKey; // the key that we performed the comparision with on node node.
    Classification classification; // 4 possibilities. see enum;

    SearchResult(RadixNode nodeParent, RadixNode node, int charsMatched, CharSequence key) {

      if (charsMatched > 0) {

        classification = Classification.PARTIAL_MATCH; // this is default
        this.nodeParent = nodeParent;
        this.node = node;
        this.searchKey = key;
        this.keySuffix = this.searchKey.subSequence(charsMatched, this.searchKey.length());
        this.prefixSuffix = this.node.prefix.subSequence(charsMatched, this.node.prefix.length());
        this.matchedSequence = this.searchKey.subSequence(0, charsMatched);

        if (this.searchKey.length() == this.node.prefix.length()
            && this.searchKey.length() == charsMatched) {
          classification = Classification.EXACT_MATCH_WITH_PREFIX;
        }

        if (this.node.prefix.length() == charsMatched
            && this.searchKey.length() > this.node.prefix.length()) {
          classification = Classification.EXACT_MATCH_WITH_PREFIX_KEY_LARGER_THAN_PREFIX;
        }

      } else { // no match
        classification = Classification.NO_MATCH;
        this.searchKey = key;
        this.nodeParent = this.node = null;
        this.matchedSequence = this.keySuffix = this.prefixSuffix = null;
      }
    }

    protected enum Classification {
      EXACT_MATCH_WITH_PREFIX,
      EXACT_MATCH_WITH_PREFIX_KEY_LARGER_THAN_PREFIX,
      PARTIAL_MATCH,
      NO_MATCH
    }
  }

  /**
   * Represet a Radix Node
   *
   * @param <T>
   */
  protected static final class RadixNode<T> implements Comparable<RadixNode<T>> {
    CharSequence prefix = "";
    T value;
    boolean hasValue = false;
    Set<RadixNode<T>> outGoingNodes = new TreeSet<>();

    @Override
    public int compareTo(RadixNode<T> o) {
      return this.prefix.toString().compareTo(o.prefix.toString());
    }
  }
}
