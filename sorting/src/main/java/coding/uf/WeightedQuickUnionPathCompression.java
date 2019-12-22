package coding.uf;

/**
 * Implementation of Weighted Quick Union Algorithm, along with path compression
 * find operation takes very very very nearly but not quite 1 (amortized)
 * union operation takes very very very nearly but not quite 1 (amortized)
 *
 * @author Harshad Shrishrimal
 */
final public class WeightedQuickUnionPathCompression extends UF {

    private final int[] sz; // size[i] = number of elements in subtree rooted at i

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code N-1}.
     * Initially, each elements is in its own set.
     *
     * @param N - the number of elements
     */
    public WeightedQuickUnionPathCompression(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
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
        int root = p;

        //find the root of the given node
        while (root != id[root])
            root = id[root];

        // path compression
        // start from given node, update each node's parent to be root
        while(p != root) {
            int parent = id[p];
            id[p] = root;
            p = parent;
        }

        return root;
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

        // both connected. nothing to be done.
        if (rootP == rootQ) return;

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }

        count--;
    }
}
