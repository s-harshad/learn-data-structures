package coding.uf;

/**
 * Implementation of Quick Union Algorithm find operation takes height of tree union operation takes
 * height of tree
 *
 * @author Harshad Shrishrimal
 */
public final class QuickUnion extends UF {

  /**
   * Initializes an empty union-find data structure with {@code n} elements {@code 0} through {@code
   * N-1}. Initially, each elements is in its own set.
   *
   * @param N - the number of elements
   */
  public QuickUnion(int N) {
    super(N);
  }

  /**
   * Returns the canonical element of the set containing element {@code p}.
   *
   * @param p an element
   * @return the canonical element of the set containing {@code p}
   * @throws IllegalArgumentException unless {@code 0 <= p < n}
   */
  public final int find(int p) {
    validate(p);
    while (p != id[p]) {
      p = id[p];
    }
    return p;
  }

  /**
   * Merges the set containing element {@code p} with the the set containing element {@code q}.
   *
   * @param p one element
   * @param q the other element
   * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
   */
  public final void union(int p, int q) {
    int root_of_p = find(p);
    int root_of_q = find(q);

    // they are connected, nothing to do
    if (root_of_p == root_of_q) return;

    id[root_of_p] = root_of_q;
    count--; // number of connected components decreased by 1
  }
}
