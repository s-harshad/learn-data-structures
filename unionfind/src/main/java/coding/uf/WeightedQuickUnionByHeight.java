package coding.uf;

final public class WeightedQuickUnionByHeight extends UF {

    private final int[] height;

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code N-1}.
     * Initially, each elements is in its own set.
     *
     * @param N - the number of elements
     */
    public WeightedQuickUnionByHeight(int N) {
        super(N);
        height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = 0;
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
        while (p != id[p])
            p = id[p];
        return p;
    }

    //notes
    // A union operation between elements in different trees either leaves the
    // height unchanged (if the two tree have different heights)
    // or increase the height by one (if the two tree are the same height).
    // by induction that the size of the tree is at least 2^height.
    // the height can increase at most lg n times.

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

        // always link the shorter root to the taller one.
        if (height[rootP] < height[rootQ]) {
            id[rootP] = rootQ;
        } else if (height[rootP] > height[rootQ]) {
            id[rootQ] = rootP;
        } else {
            id[rootQ] = rootP;
            height[rootP]++;
        }

        count--;
    }
}
