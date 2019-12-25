package coding.trie;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class RadixTrie {

  private Node root;
  private int n;

  public void put(String key, Object value) {
    if (root == null) {
      root = new Node();
      root.childerns.add(put(null, key, value, false));
      return;
    }
    put(key, value, root);
  }

  public Object get(String key) {
    Node x = get(key, root);
    return x == null ? null : x.value;
  }

  Node get(String key, Node current) {

    if (key.isBlank() || key.isEmpty()) return current; //exit condition

    if (isPrefixSharedByAnyChild(key, current)) {
      current = nodeWherePrefixIsShared(key, current);
      key = key.substring(largestPrefixLength(key, current.prefix));
      return get(key, current);
    }

    return null;
  }

  void put(String key, Object value, Node node) {

    if (!isPrefixSharedByAnyChild(key, node)) {
      node.childerns.add(put(null, key, value, false));
      return;
    } else {

      Node n = nodeWherePrefixIsShared(key, node);
      int len = largestPrefixLength(key, n.prefix);

      // ExactMatch
      if (key.length() == n.prefix.length() && key.length() == len) {
        put(n, key, value, true); // update
        return;
      }

      if (len > 0) {

        if (len == n.prefix.length() && key.length() > n.prefix.length()) {
          String leftOverKey = key.substring(len);
          put(leftOverKey, value, n);
          return;
        }

        // split
        String commonPrefix = n.prefix.substring(0, len);
        String leftOverPrefix = n.prefix.substring(len);

        if (!leftOverPrefix.isEmpty() && !leftOverPrefix.isBlank()) {
          Node newNode = put(null, leftOverPrefix, n.value, true);
          //          newNode.childerns.addAll(n.childerns);
          //          n.childerns.clear();
          //          n.childerns.add(newNode);
          newNode.childerns = n.childerns;
          n.childerns = new TreeSet();
          n.childerns.add(newNode);
        }

        n.prefix = commonPrefix;
        n.value = null;
        n.hasValue = false;
        put(key, value, node);
        return;
      }
    }
    return;
  }

  private boolean isKeyCompletelyMatchingPrefix(String key, String prefix) {
    int len = largestPrefixLength(key, prefix);
    return len > 0 && len == key.length() && key.length() > prefix.length();
  }

  private Node nodeWherePrefixIsShared(String prefix, Node x) {
    if (x == null) return null;
    for (Node n : x.childerns) {
      if (largestPrefixLength(prefix, n.prefix) > 0) return n;
    }
    return null;
  }

  private boolean isPrefixSharedByAnyChild(String prefix, Node x) {
    if (x == null) return false;
    for (Node n : x.childerns) {
      if (largestPrefixLength(prefix, n.prefix) > 0) return true;
    }
    return false;
  }

  private Node put(Node x, String prefix, Object value, boolean isReplacing) {
    if (!isReplacing) n++;
    if (x == null) x = new Node();
    x.prefix = prefix;
    x.value = value;
    x.hasValue = true;
    return x;
  }

  public static int largestPrefixLength(CharSequence a, CharSequence b) {
    int len = 0;
    int to = Math.min(a.length(), b.length());
    for (; len < to && a.charAt(len) == b.charAt(len); len++) {}
    return len;
  }

  /**
   * Prints a subtree to <code>System.out</code>.
   *
   * @param node the subtree
   * @param outputPrefix prefix to be printed to output
   */
  static <V extends Serializable> void dumpTree(Node node, String outputPrefix) {
    if (node.hasValue) System.out.format("%s{%s : %s}%n", outputPrefix, node.prefix, node.value);
    else System.out.format("%s{%s}%n", outputPrefix, node.prefix, node.value);

    for (Node child : node.childerns) dumpTree(child, outputPrefix + "\t");
  }

  public static void main(String[] args) {

    System.out.println("Hello World");
    RadixTrie st = new RadixTrie();

    // st.put("cat", "nope");
    st.put("cargo", "");
    st.put("cars", "Honda Kia");
    st.put("car", "");
    st.put("computer", "");
    st.put("cargo", "");
    st.put("cam", "");
    st.put("dummy", "");
    st.put("name", "");
    st.put("compute", "");
    st.put("commute", "");

    //System.out.println("st.get(\"cars\") = " + st.get("cars"));
    System.out.println("st.get(\"cari\") = " + st.get("cari"));

    dumpTree(st.root, "");
  }

  static class Node implements Comparable<Node> {
    String prefix = "";
    Object value;
    boolean hasValue;
    Set<Node> childerns = new TreeSet<>();

    @Override
    public String toString() {
      return "Node{"
          + "prefix='"
          + prefix
          + '\''
          + ", value="
          + value
          + ", hasValue="
          + hasValue
          + ", childerns="
          + childerns
          + '}';
    }

    @Override
    public int compareTo(Node node) {
      return this.prefix.compareTo(node.prefix);
    }
  }
}
