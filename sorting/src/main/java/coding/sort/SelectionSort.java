package coding.sort;

/**
 * Implementation of Selection Sort algorithm.
 *
 * @author Harshad Shrishrimal
 */
public final class SelectionSort {

    // private Constructor.
    // Object cannot be instantiated
    private SelectionSort() {
    }

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        // divides the array into 2 parts
        // left part is sorted sub-array. right is unsorted.
        // pick the minimum from the right section
        // replace minimum with left-most element of right section
        // left-most element becomes part of sorted sub-array

        int N = array.length;

        for (int left = 0; left < N; left++) {
            int currentIndexOfMinimum = left;

            // pick the minimum from the right section
            for (int right = left + 1; right < N; right++) {
                if (Utilities.less(array, right, currentIndexOfMinimum)) {
                    currentIndexOfMinimum = right;
                }
            }

            // replace minimum with left-most element of right section
            Utilities.exchange(array, left, currentIndexOfMinimum);
        }
    }
}
