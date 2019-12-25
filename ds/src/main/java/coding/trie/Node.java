package coding.trie;

abstract class Node<T> {
  T val;

  abstract Node getChild(int c);

  abstract void setChild(int c, Node<T> node);
}
