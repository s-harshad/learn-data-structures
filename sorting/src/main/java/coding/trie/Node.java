package coding.trie;

class Node<VALUE> {

  private static final int R = 256; // Extended ASCII

  VALUE val;
  Node<VALUE>[] next = new Node[R]; // extended ascii
}
