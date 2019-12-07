package coding.algo;

/**
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
     */
    static final void exchange(Comparable[] array, int leftIndex, int rightIndex) {
        Comparable temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }

    /**
     * Compare 2 elements and return {@code true} if element represented by {@code firstIndex} is
     * less than {@code secondIndex}; {@code false} otherwise.
     *
     * @param array       array of elements of which 2 will be compared
     * @param firstIndex  index of first element
     * @param secondIndex index of second element
     * @return {@code true} if element represented by {@code firstIndex} is less than {@code secondIndex}; {@code false} otherwise.
     */
    static final boolean less(Comparable[] array, int firstIndex, int secondIndex) {
        return array[firstIndex].compareTo(array[secondIndex]) < 0;
    }



}
