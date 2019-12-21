package coding.sort;

/**
 * Implementation of Heap Sort Algorithm.
 *
 * @author Harshad Shrishrimal
 */
public final class HeapSort {

    // private Constructor.
    // Object cannot be instantiated
    private HeapSort() {
    }

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        // build MAX heap.
        HeapUtilities.heapify(array, Comparable::compareTo);

        //Root contains the largest element, exchange with the last element
        //reduce the size of heap by 1 and repeat
        int n = array.length - 1;
        while (n >= 0) {
            Utilities.exchange(array, 0, n);
            HeapUtilities.heapify(array, Comparable::compareTo, 0, n--);
        }
    }

}
