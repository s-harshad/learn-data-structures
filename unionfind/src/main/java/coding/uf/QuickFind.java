package coding.uf;


/**
 * Implementation of Quick Find Algorithm
 * find operation takes O(1)
 * union operation takes O(n)
 *
 * @author Harshad Shrishrimal
 */
final public class QuickFind extends UF {

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code N-1}.
     * Initially, each elements is in its own set.
     *
     * @param N - the number of elements
     */
    public QuickFind(int N) {
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
        return id[p];
    }


    /**
     * Merges the set containing element {@code p} with the
     * the set containing element {@code q}.
     *
     * @param p one element
     * @param q the other element
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public final void union(int p, int q) {
        // already connected, do nothing
        if (isConnected(p, q)) return;

        int id_of_P = id[p];
        int id_of_Q = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == id_of_Q) {
                id[i] = id_of_P;
            }
        }

        // number of connected components decreased by 1
        count--;
    }

}
