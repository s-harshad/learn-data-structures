package coding.sort;

/**
 * Implementation of Merge Sort
 *
 * @author Harshad Shrishrimal
 */
public final class MergeSortBottomUp {

    // private Constructor.
    // Object cannot be instantiated
    private MergeSortBottomUp() {
    }

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        int N = array.length;

        for (int sz = 1; sz < N; sz = 2 * sz) {
            for (int lo = 0; lo < N; lo = lo + sz + sz) {
                int hi = Math.min(N - 1, lo + sz + sz - 1);
                int mid = lo + sz - 1;
                Utilities.merge(array, lo, mid, hi);
            }
        }
    }
}
