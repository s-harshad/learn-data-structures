package coding.sort;

/**
 * Class that exposes methods related to paritioning a given collection
 *
 * @author Harshad Shrishrimal
 */
final class PartitionUtilities {

    // private Constructor.
    // Object cannot be instantiated
    private PartitionUtilities() {
    }

    /**
     * Partition the sub-array array[startIndex ..... endIndex], so that
     * array[startIndex ..... lt - 1] < array[lt ..... gt] < array[gt + 1 ..... endIndex]
     * All elements between [lt ..... gt] are EQUAL
     *
     * @param array      array to be partitioned
     * @param startIndex start index for the array
     * @param endIndex   end index for the array
     * @param <T>        generic class/object that implements/extends {@code Comparable}
     * @return start and end index positions where the partition elements are equal, [lt,gt].
     */
    static final <T extends Comparable<T>> int[] partitionDuplicateEntriesInArray(T[] array, int startIndex, int endIndex) {

        int lt = startIndex;
        int gt = endIndex;

        // the element that we will be comparing against
        T v = array[startIndex];
        for (int i = startIndex; i <= gt; ) {
            int cmp = array[i].compareTo(v);
            if (cmp < 0) {
                // current element is less then the comparing element
                // exchange and increment
                Utilities.exchange(array, lt++, i++);
            } else if (cmp > 0) {
                // current element is greater then the comparing element
                // exchange and decrement
                Utilities.exchange(array, i, gt--);
            } else {
                // both elements are equal
                // no exchange.
                i++;
            }
        }

        // all elements between [lt .. gt] are equal
        return new int[]{lt, gt};
    }

    /**
     * Partition the sub array array[startIndex ..... endIndex], so that
     * array[startIndex ..... rightIndex - 1] <= array[rightIndex] < array[rightIndex + 1 ..... endIndex]
     *
     * @param array      array to be partitioned
     * @param startIndex start index for the array
     * @param endIndex   end index for the array
     * @param <T>        generic class/object that implements/extends {@code Comparable}
     * @return correct index position of the partition element
     */
    static final <T extends Comparable<T>> int partition(T[] array, int startIndex, int endIndex) {

        // element represented by
        // startIndex will ultimately become the partitioned element

        int leftIndex = startIndex;
        int rightIndex = endIndex + 1;

        while (true) {
            // keep incrementing leftIndex till we find a position
            // where the element is greater than startIndex.
            // break if we are reaching the end of array
            while (Utilities.less(array, ++leftIndex, startIndex)) {
                if (leftIndex == endIndex) break;
            }

            // keep decrementing rightIndex till we find a position
            // where the element is less than startIndex.
            // break if we are reaching the start of array
            while (Utilities.less(array, startIndex, --rightIndex)) {
                if (rightIndex == startIndex) break;
            }

            // exit condition for the outer loop
            // break, if the index cross
            if (leftIndex >= rightIndex) break;

            // for the given partitioning element
            // elements at position leftIndex & rightIndex
            // are out of order; swap them
            Utilities.exchange(array, leftIndex, rightIndex);
        }

        // put the partitioning element in its position
        Utilities.exchange(array, startIndex, rightIndex);

        // rightIndex is the position of the partitioned element
        return rightIndex;
    }
}
