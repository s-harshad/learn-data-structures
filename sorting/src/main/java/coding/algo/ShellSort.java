package coding.algo;

/**
 * Implementation of Shell Sort
 *
 * @author Harshad Shrishrimal
 */
public final class ShellSort {

    // private Constructor.
    // Object cannot be instantiated
    private ShellSort() {
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

        // h-sort increment sequence
        // 1, 4, 13, 40, 121, ....
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        for (; h >= 1; h = h / 3) {
            // h-sort the array, using insertion sort.
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && Utilities.less(array, j, j - h); j = j - h) {
                    Utilities.exchange(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
