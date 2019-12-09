package coding.algo;

import java.util.Comparator;

/**
 * Class that exposes methods for comparing elements in collection.
 *
 * @author Harshad Shrishrimal
 */
final class Utilities {

    // private Constructor.
    // Object cannot be instantiated
    private Utilities() {
    }

    /**
     * Swap 2 elements
     *
     * @param array      array which has the elements to swap
     * @param leftIndex  index of first element
     * @param rightIndex index of second element
     * @param <T>        generic
     */
    static final <T> void exchange(T[] array, int leftIndex, int rightIndex) {
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
    static final boolean less(Comparable[] array, int firstIndex, int secondIndex) {
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
    static final <T> boolean lessOrEqual(T[] array, Comparator<T> comparator, int firstIndex, int secondIndex) {
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
    static final <T> boolean less(T[] array, Comparator<T> comparator, int firstIndex, int secondIndex) {
        return comparator.compare(array[firstIndex], array[secondIndex]) < 0;
    }
}
