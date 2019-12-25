package coding.trie;

import java.util.Set;
import java.util.TreeSet;

class Node implements Comparable<Node> {
  String prefix;
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
