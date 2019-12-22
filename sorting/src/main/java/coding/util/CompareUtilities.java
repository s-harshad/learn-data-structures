package coding.util;

import java.util.Comparator;

/**
 * Class that exposes methods for comparing elements in collection.
 *
 * @author Harshad Shrishrimal
 */
final public class CompareUtilities {

    // private Constructor.
    // Object cannot be instantiated
    private CompareUtilities() {
    }

    /**
     * Swap 2 elements
     *
     * @param array      array which has the elements to swap
     * @param leftIndex  index of first element
     * @param rightIndex index of second element
     * @param <T>        generic
     */
    public static final <T> void exchange(T[] array, int leftIndex, int rightIndex) {
        T temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }

    /**
     * Compare 2 elements
     *
     * @param array       array of elements of which 2 will be compared
     * @param firstIndex  index of first element
     * @param secondIndex index of second element
     * @return {@code true} if element represented by {@code firstIndex} is less than {@code secondIndex}; {@code false} otherwise.
     */
    public static final boolean less(Comparable[] array, int firstIndex, int secondIndex) {
        return array[firstIndex].compareTo(array[secondIndex]) < 0;
    }

    /**
     * Compare 2 elements
     *
     * @param array       array of elements of which 2 will be compared
     * @param firstIndex  index of first element
     * @param secondIndex index of second element
     * @param <T>         generic class, whose collection can be compared.
     * @return {@code true} if element represented by {@code firstIndex} is less or equal to {@code secondIndex}; {@code false} otherwise.
     */
    public static final <T> boolean lessOrEqual(T[] array, Comparator<T> comparator, int firstIndex, int secondIndex) {
        int cmp = comparator.compare(array[firstIndex], array[secondIndex]);
        return cmp == 0 || cmp < 0;
    }

    /**
     * Compare 2 elements
     *
     * @param array       array of elements of which 2 will be compared
     * @param comparator  {@code Comparator} to use for comparison
     * @param firstIndex  index of first element
     * @param secondIndex index of second element
     * @param <T>         generic class, whose collection can be compared.
     * @return {@code true} if element represented by {@code firstIndex} is less than {@code secondIndex}; {@code false} otherwise.
     */
    public static final <T> boolean less(T[] array, Comparator<T> comparator, int firstIndex, int secondIndex) {
        return comparator.compare(array[firstIndex], array[secondIndex]) < 0;
    }

    /**
     * Merge Sorted Arrays.
     *
     * @param array Collection that contains data
     * @param lo    start of array
     * @param mid   mid. Indicates where the first array ends.
     * @param hi    end of array
     * @param <T>   generic class
     */
    public static final <T extends Comparable<T>> void merge(Comparable[] array, int lo, int mid, int hi) {

        int leftIndx = lo;
        int rightIndx = mid + 1;

        // short optimization
        if (rightIndx <= hi && !CompareUtilities.less(array, rightIndx, mid)) {
            return; // no need to merge. everything is in order.
        }

        // copy to an auxillary array
        Comparable<T>[] aux = new Comparable[array.length];
        for (int i = lo; i <= hi; i++) {
            aux[i] = array[i];
        }

        for (int k = lo; k <= hi; k++) {
            if (leftIndx > mid) { // left array is complete merge everything from right
                array[k] = aux[rightIndx++];
            } else if (rightIndx > hi) { // right array is complete merge everything from left
                array[k] = aux[leftIndx++];
            } else if (less(aux, leftIndx, rightIndx)) { // check which is smaller
                array[k] = aux[leftIndx++];
            } else {
                array[k] = aux[rightIndx++];
            }
        }

    }
}
