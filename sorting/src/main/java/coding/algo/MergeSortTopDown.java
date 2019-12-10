package coding.algo;

/**
 * Implementation of Merge Sort
 *
 * @author Harshad Shrishrimal
 */
public final class MergeSortTopDown {

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private static final <T extends Comparable<T>> void sort(T[] array, int lo, int hi) {

        // exit condition for recursion
        if (lo >= hi) return;

        // get the mid
        int mid = lo + (hi - lo) / 2;

        // recursively sort
        sort(array, lo, mid);
        sort(array, mid + 1, hi);

        // merge
        Utilities.merge(array, lo, mid, hi);
    }

}
