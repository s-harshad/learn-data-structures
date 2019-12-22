package coding.uf;

/**
 * Implementation of Weighted Quick Union Algorithm
 * find operation takes O(lgN)
 * union operation takes O(lgN)
 *
 * @author Harshad Shrishrimal
 */
public final class WeightedQuickUnion extends UF {

    private final int[] sz; // size[i] = number of elements in subtree rooted at i

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code N-1}.
     * Initially, each elements is in its own set.
     *
     * @param N - the number of elements
     */
    public WeightedQuickUnion(int N) {
        super(N);
        sz = new int[N];

        for (int i = 0; i < N; i++) { // initally all components have size 1
            sz[i] = 1;
        }
    }

    /**
     * Returns the canonical element of the set containing element {@code p}.
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    @Override
    int find(int p) {
        validate(p);
        while (p != id[p]) {
            p = id[p];
        }
        return p;
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
    @Override
    void union(int p, int q) {

        int rootP = find(p);
        int rootQ = find(q);

        // already connected. nothing to do.
        if (rootP == rootQ)
            return;

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP; // rootP will becomes the parent for rootQ
            sz[rootP] += sz[rootQ]; // update size. add the size of newly added node
        }

        count--;
    }
}
