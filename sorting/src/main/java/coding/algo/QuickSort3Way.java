package coding.algo;

/**
 * Implementation of 3 Way Quick Sort Algorithm.
 * @author Harshad Shrishrimal
 */
public final class QuickSort3Way {

    // private Constructor.
    // Object cannot be instantiated
    private QuickSort3Way() {
    }

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        // sort the entire array
        sort(array, 0, array.length - 1);
    }

    // QuickSort the sub array from array[lo ..... hi]
    private static final <T extends Comparable<T>> void sort(T[] array, int lo, int hi) {
        // exit condition for recursion
        if (lo >= hi)
            return;

        // partition the array, and get the indices where the partitioned elements are equal
        int[] indices = PartitionUtilities.partitionDuplicateEntriesInArray(array, lo, hi);

        // recursively sort the left and right side, excluding partitioned element
        sort(array, lo, indices[0] - 1);
        sort(array, indices[1] + 1, hi);
    }

}
