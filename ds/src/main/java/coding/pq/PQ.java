package coding.pq;

import coding.util.HeapUtilities;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation of Priority Queue. This single class implements both MIN and MAX PQ.
 *
 * @param <KEY> generic
 */
public final class PQ<KEY extends Comparable<KEY>> {

  private final Comparator<KEY> comparator; // determines MAX or MIN PQ
  private KEY[] pq; // data-structure to hold the keys.
  private int numberOfElements; // current total

  /**
   * Create an Empty PQ Data Structure with capacity specified by {@code initCapacity}
   *
   * @param initCapacity initial capacity of collection
   * @param comparator {code Comparator} determines whether it's a min or max PQ
   */
  public PQ(int initCapacity, Comparator<KEY> comparator) {
    pq = (KEY[]) new Comparable[initCapacity];
    numberOfElements = 0;
    this.comparator = comparator;
  }

  /**
   * Create a Priority Queue for the given {@code keys} collection.
   *
   * @param keys collection for with PQ needs to be created
   * @param comparator comparator {code Comparator} determines whether it's a min or max PQ
   */
  public PQ(KEY[] keys, Comparator<KEY> comparator) {

    this(keys.length, comparator);

    // copy
    for (int i = 0; i < keys.length; i++) {
      pq[i] = keys[i];
    }
    numberOfElements = keys.length;

    HeapUtilities.heapify(pq, this.comparator);
  }

  /**
   * Insert a new key into PQ
   *
   * @param key data to add
   */
  public void insert(KEY key) {
    // resize if necessary
    if (numberOfElements == pq.length) {
      resize(2 * numberOfElements);
    }

    // Add key to end of array
    pq[numberOfElements++] = key;

    // heap order may be violated; sink up
    HeapUtilities.bottomUpHeapify(pq, numberOfElements - 1, comparator);
  }

  /**
   * Remove the current min or max key from PQ. Min or Max depends on the {@code Comparator} used.
   *
   * @return removed key
   */
  public KEY delete() {
    if (isEmpty()) throw new NoSuchElementException("PQ is Empty");

    KEY k = pq[0];
    pq[0] = pq[numberOfElements - 1]; // move the last element to the beginning of array
    pq[numberOfElements - 1] = null;
    numberOfElements--;

    // heap order may be violated; swim down
    HeapUtilities.heapify(pq, comparator, 0, numberOfElements);

    if (numberOfElements > 0 && (numberOfElements == (pq.length - 1 / 4))) {
      resize(pq.length / 2);
    }

    return k;
  }

  /**
   * Retrieve and return the current min or max key from PQ. Min or Max depends on the {@code
   * Comparator} used.
   *
   * @return current min or max
   */
  public final Optional<KEY> peek() {
    if (isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(pq[0]);
  }

  /**
   * Number of keys in PQ
   *
   * @return Number of keys in PQ
   */
  public final int size() {
    return numberOfElements;
  }

  /**
   * Check if PQ is empty
   *
   * @return {@code true} if PQ is empty; {@code false} otherwise.
   */
  public final boolean isEmpty() {
    return numberOfElements == 0;
  }

  /**
   * Resize Array
   *
   * @param capacity new length
   */
  private final void resize(int capacity) {
    KEY[] temp = (KEY[]) new Object[capacity];
    // copy existing elements
    for (int i = 0; i < numberOfElements; i++) {
      temp[i] = pq[i];
    }
    pq = temp;
  }
}
