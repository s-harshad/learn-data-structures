package coding.util;

import java.util.Comparator;

/**
 * Class that exposes methods to Heapify a given collection.
 *
 * @author Harshad Shrishrimal
 */
public final class HeapUtilities {

  // private Constructor.
  // Object cannot be instantiated
  private HeapUtilities() {}

  /**
   * Build a Binary Heap from the given array
   *
   * @param array array to heapify
   * @param comparator comparision criteria; specifies MAX or MIN heap
   * @param <T> generic class
   */
  public static final <T> void heapify(T[] array, Comparator<T> comparator) {
    // start is the index of the last parent / non-leaf node in the binary tree
    int N = array.length - 1; // last index in array
    int start = (N - 1) / 2;

    // start from last non-leaf node and perform heapify operation till the root node, in reverse.
    while (start >= 0) {
      heapify(array, comparator, start, array.length);
      start--;
    }
  }

  /**
   * Check if the given collection {@code array} is heapified.
   *
   * @param array Collection to check
   * @param comparator comparision criteria; specifies MAX or MIN heap
   * @param <T> generic class
   * @return {@code true} if collection is Heapified {@code false} otherwise.
   */
  public static final <T> boolean isArrayHeapified(T[] array, Comparator<T> comparator) {

    int TOTAL_ELEMENTS = array.length;

    // start is the index of the last parent / non-leaf node in the binary tree
    int N = TOTAL_ELEMENTS - 1;
    int indexOfLastParentNode = (N - 1) / 2;

    // for each root check the children's and make sure
    // they both are less (MAX heap) or greater then their root
    for (int idxRoot = 0; idxRoot <= indexOfLastParentNode; idxRoot++) {

      int idxLeftChild = 2 * idxRoot + 1;
      int idxRightChild = idxLeftChild + 1;

      // default to true. it indicates that there is no child.
      boolean compareRootWithLeftChild = true;
      boolean compareRootWithRightChild = true;

      // left child exists; do the comparison with root.
      if (idxLeftChild < TOTAL_ELEMENTS) {
        compareRootWithLeftChild =
            CompareUtilities.lessOrEqual(array, comparator, idxLeftChild, idxRoot);
      }

      // right child exists; do the comparison with root
      if (idxRightChild < TOTAL_ELEMENTS) {
        compareRootWithRightChild =
            CompareUtilities.lessOrEqual(array, comparator, idxRightChild, idxRoot);
      }

      if (!(compareRootWithLeftChild && compareRootWithRightChild)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Build a Binary Heap, for the given collection. With this method, user can specify total number
   * of elements to consider for heap creation. This method is sometimes also called Top Down
   * Heapify
   *
   * @param array
   * @param comparator comparision criteria; specifies MAX or MIN heap
   * @param idxRoot where to start the heap to build from. Usually it's root. {@code 0}
   * @param numberOfElement number of elements to consider. Usually it's total elements in
   *     collection.
   * @param <T> generic class
   */
  public static final <T> void heapify(
      T[] array, Comparator<T> comparator, int idxRoot, int numberOfElement) {

    // given the current root {@code idxRoot} find, which is max of the 3 elements
    // root, left child or right child.

    int idxLeftChild = 2 * idxRoot + 1;
    int idxRightChild = idxLeftChild + 1;

    int idxOfMaxElement = idxRoot;

    // 1st condition avoid IndexOutOfBounds
    // check if leftChild is greater than root
    if (idxLeftChild < numberOfElement
        && CompareUtilities.less(array, comparator, idxRoot, idxLeftChild)) {
      idxOfMaxElement = idxLeftChild;
    }

    // 1st condition avoid IndexOutOfBounds
    // check if rightChild is greater than current maximum
    if (idxRightChild < numberOfElement
        && CompareUtilities.less(array, comparator, idxOfMaxElement, idxRightChild)) {
      idxOfMaxElement = idxRightChild;
    }

    // if largest is not the root, then swap; and recursively heapify
    if (idxOfMaxElement != idxRoot) {
      CompareUtilities.exchange(array, idxOfMaxElement, idxRoot);
      heapify(array, comparator, idxOfMaxElement, numberOfElement);
    }
  }

  /**
   * Starting from the end, compare child with it's parent and exchange if necessary. This is used
   * in insert operation of PQ
   *
   * @param array collection containing the date
   * @param startIdx end index to start from
   * @param comparator Comparing criteria
   * @param <T> generic
   */
  public static final <T extends Comparable<T>> void bottomUpHeapify(
      T[] array, int startIdx, Comparator<T> comparator) {
    while (startIdx >= 0
        && CompareUtilities.less(array, comparator, (startIdx - 1) / 2, startIdx)) {
      // violation. exchange parent with child.
      CompareUtilities.exchange(array, (startIdx - 1) / 2, startIdx);
      // move up
      startIdx = (startIdx - 1) / 2;
    }
  }
}
