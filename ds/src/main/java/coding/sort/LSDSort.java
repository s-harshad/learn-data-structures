package coding.sort;

import java.util.Arrays;

/**
 * Implementation of LSD Sorting Algorithm.
 *
 * @author Harshad Shrishrimal
 */
public final class LSDSort {

  // private Constructor.
  // Object cannot be instantiated
  private LSDSort() {}

  /**
   * Sort the given array
   *
   * @param array Collection to sort
   */
  public static final void sort(String[] array) {
    // check all Strings of same length
    if (!areAllStringsSameLength(array)) {
      throw new IllegalArgumentException("All items must have same length");
    }
    sort(array, array[0].length());
  }

  /**
   * Sort the given array
   *
   * @param array Collection to sort
   */
  public static final void sort(Integer[] array) {

    final int BITS_PER_INT = 32; // each integer is 32 bits / 4 bytes
    final int BITS_PER_BYTE = 8;
    final int R = 1 << 8; // 256
    final int MASK = R - 1; // 255 / 0xFF / 1111 1111
    final int w = BITS_PER_INT / BITS_PER_BYTE; // each int is 4 bytes

    int N = array.length;
    int[] aux = new int[N];

    for (int d = 0; d < w; d++) {

      // compute frequency counts of each character
      int[] count = new int[R + 1];
      for (int i = 0; i < N; i++) {
        int c = (array[i] >> BITS_PER_BYTE * d) & MASK;
        count[c + 1]++;
      }

      // computer cumulatives
      for (int i = 0; i < R; i++) count[i + 1] += count[i];

      // for most significant digit   0x80 / 128 - 0xFF/255
      // comes before 0x00 - 0x7f / 127
      if (d == w - 1) {
        int shift1 = count[R] - count[R / 2];
        // indicates there are some negative numbers.
        // we need to do shifting
        if (shift1 != 0) {
          int shift2 = count[R / 2];
          for (int r = 0; r < R / 2; r++) {
            count[r] += shift1;
          }
          for (int r = R / 2; r < R; r++) {
            count[r] -= shift2;
          }
        }
      }

      // move data
      for (int i = 0; i < N; i++) {
        int c = (array[i] >> BITS_PER_BYTE * d) & MASK;
        aux[count[c]++] = array[i];
      }

      // copy back
      for (int i = 0; i < N; i++) {
        array[i] = aux[i];
      }
    }
  }

  /**
   * Rearranges the array of w-character strings in order.
   *
   * @param array data to sort
   * @param w number of characters per string
   */
  private static final void sort(String[] array, int w) {

    int N = array.length;
    int R = 256; // extended ASCII alphabet size
    String[] aux = new String[N];

    // starting from right, sort the Collection 1 alphabet at a time.
    for (int d = w - 1; d >= 0; d--) {

      // compute frequency counts of each character
      int[] count = new int[R + 1];
      for (int i = 0; i < N; i++) count[array[i].charAt(d) + 1]++;

      // compute cumulatives
      for (int r = 0; r < R; r++) count[r + 1] += count[r];

      // move data
      for (int i = 0; i < N; i++) aux[count[array[i].charAt(d)]++] = array[i];

      // copy data back to array
      for (int i = 0; i < N; i++) array[i] = aux[i];
    }
  }

  /**
   * Check all items have same length
   *
   * @param array collection to verify
   * @return {@code true} if all {@code String}'s have same length; {@code false} otherwise
   */
  private static boolean areAllStringsSameLength(String[] array) {
    final int length = array[0].length();
    return Arrays.stream(array).parallel().allMatch(str -> str.length() == length);
  }
}
