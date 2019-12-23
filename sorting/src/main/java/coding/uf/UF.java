package coding.uf;

/**
 * Abstract Class for UnionFind Data Structure.
 *
 * @author Harshad Shrishrimal
 */
abstract class UF {

  // id[i] = component identifier of i in case of QuickFind
  // id[i] = Parent of i in case of QuickUnion
  protected final int[] id;
  protected int count; // the number of connected components

  /**
   * Initializes an empty union-find data structure with {@code n} elements {@code 0} through {@code
   * N-1}. Initially, each elements is in its own set.
   *
   * @param N - the number of elements
   */
  public UF(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  /**
   * Returns the canonical element of the set containing element {@code p}.
   *
   * @param p an element
   * @return the canonical element of the set containing {@code p}
   * @throws IllegalArgumentException unless {@code 0 <= p < n}
   */
  abstract int find(int p);

  /**
   * Merges the set containing element {@code p} with the the set containing element {@code q}.
   *
   * @param p one element
   * @param q the other element
   * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
   */
  abstract void union(int p, int q);

  /**
   * Returns the number of connected components.
   *
   * @return the number of sets (between {@code 1} and {@code n})
   */
  public final int count() {
    return count;
  }

  /**
   * Returns true if the two elements are in the same set.
   *
   * @param p one element
   * @param q the other element
   * @return {@code true} if {@code p} and {@code q} are in the same set; {@code false} otherwise
   * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
   */
  public final boolean isConnected(int p, int q) {
    // if component identifiers are the same, then they are connected.
    return find(p) == find(q);
  }

  /**
   * verify that {@code idx} is a valid index in the array.
   *
   * @param idx index to verify
   * @throws IllegalArgumentException if {@code idx < 0 or idx >= N}
   */
  final void validate(int idx) {
    int n = id.length;
    if (idx < 0 || idx >= n) {
      throw new IllegalArgumentException(
          String.format("index %d is not between 0 and %d", idx, n - 1));
    }
  }
}
