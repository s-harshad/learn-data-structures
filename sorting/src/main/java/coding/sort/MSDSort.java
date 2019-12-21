package coding.sort;

/**
 * Implementation of MSD Sorting Algorithm
 *
 * @author Harshad Shrishrimal
 */
public class MSDSort {

    private static final int R = 256;   // extended ASCII alphabet size
    private final static int BITS_PER_INT = 32; // each integer is 32 bits / 4 bytes
    private final static int BITS_PER_BYTE = 8;

    public static final void sort(String[] array) {
        int N = array.length;
        String[] aux = new String[N];
        sort(array, 0, N - 1, 0, aux);
    }


    private static int charAt(String str, int d) {
        if (d >= str.length()) return -1;
        return str.charAt(d);
    }

    /**
     * Sort the given array
     *
     * @param array collection to sort
     * @param lo    start index
     * @param hi    end index
     * @param d     digit in the integer
     * @param aux   auxillary array for copying data.
     */
    private static void sort(String[] array, int lo, int hi, int d, String[] aux) {

        //exit condition for recursion
        if (hi <= lo) {
            return;
        }

        //compute frequency
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(array[i], d);
            count[c + 2]++;
        }

        // transform count to indices
        for (int i = 0; i < R + 1; i++) {
            count[i + 1] += count[i];
        }

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(array[i], d);
            aux[count[c + 1]++] = array[i];
        }
        // copy back
        for (int i = lo; i <= hi; i++) {
            array[i] = aux[i - lo];
        }

        for (int r = 0; r < R; r++) {
            sort(array, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
        }
    }

}
