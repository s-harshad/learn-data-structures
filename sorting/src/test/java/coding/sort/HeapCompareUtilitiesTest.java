package coding.sort;

import coding.providers.HeapifyInputProvider;
import coding.providers.TestData;
import coding.util.HeapUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.Comparator;

@RunWith(JUnitPlatform.class)
public class HeapCompareUtilitiesTest {

  @DisplayName("HeapUtilities MAX Heapify Test")
  @ParameterizedTest(name = "Running permutation {index}")
  @ArgumentsSources({@ArgumentsSource(HeapifyInputProvider.class)})
  void maxHeapifyTest(TestData arguments) {
    Integer[] input = arguments.getInput();
    Comparator<Integer> comparator = Integer::compareTo; // max heap
    HeapUtilities.heapify(input, comparator);
    Assertions.assertEquals(true, HeapUtilities.isArrayHeapified(input, comparator));
  }

  @DisplayName("HeapUtilities MIN Heapify Test")
  @ParameterizedTest(name = "Running permutation {index}")
  @ArgumentsSources({@ArgumentsSource(HeapifyInputProvider.class)})
  void minHeapifyTest(TestData arguments) {
    Integer[] input = arguments.getInput();
    Comparator<Integer> comparator = Comparator.reverseOrder(); // min heap
    HeapUtilities.heapify(input, comparator);
    Assertions.assertEquals(true, HeapUtilities.isArrayHeapified(input, comparator));
  }

  @Test
  @DisplayName("should return false for isArrayHeapified")
  void testArrayHeapified_1() {
    Integer[] input = {10, 11, 1};
    Comparator<Integer> comparator = Integer::compareTo;
    // method under test
    boolean actual = HeapUtilities.isArrayHeapified(input, comparator);
    Assertions.assertEquals(false, actual);
  }
}
