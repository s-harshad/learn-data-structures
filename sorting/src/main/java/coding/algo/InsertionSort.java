package coding.algo;

/**
 * Implementation of Insertion Sort algorithm.
 * To Sort a randomly-ordered array with distinct keys,
 * Insertion sort uses ~ (1/4)N*N compares and ~ (1/4)N*N exchanges on average
 * <p>
 * It is not suitable for sorting large arbitrary arrays. More precisely,
 * the number of exchanges is exactly equal to the number of inversions.
 * So, for example, it sorts a partially-sorted array in linear time.
 *
 * @author Harshad Shrishrimal
 */
public final class InsertionSort {

    // private Constructor.
    // Object cannot be instantiated
    private InsertionSort() {
    }

    /**
     * Sort the given array.
     * The elements in the given array should implement {@code Comparable}.
     *
     * @param <T>   generic class/object that implements/extends {@code Comparable}
     * @param array array to sort
     */
    public static final <T extends Comparable<T>> void sort(T[] array) {
        // remove one element from input data
        // find location where it belongs & insert it there
        // repeat till no elements remain
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && Utilities.less(array, j, j - 1); j--) {
                Utilities.exchange(array, j, j - 1);
            }
        }
    }
}
