package coding.pq;

import coding.providers.HeapifyInputProvider;
import coding.providers.TestData;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class PQTest {

  @DisplayName("PQ MAX Test")
  @ParameterizedTest(name = "Running permutation {index}")
  @ArgumentsSources({@ArgumentsSource(HeapifyInputProvider.class)})
  void maxPQ_delete_Test(TestData arguments) {
    Integer[] input = arguments.getInput();

    // expected output. it's going to be reverse sorted.
    Integer[] expected = Arrays.copyOf(input, input.length);
    Arrays.sort(expected, Comparator.reverseOrder());

    // method/class under test
    PQ<Integer> pq = new PQ<Integer>(input, Integer::compareTo); // MAX PQ

    Integer[] actual = new Integer[input.length];
    for (int i = 0; i < input.length; i++) {
      actual[i] = pq.delete();
    }

    Assertions.assertArrayEquals(expected, actual);
  }

  @DisplayName("PQ MIN Test")
  @ParameterizedTest(name = "Running permutation {index}")
  @ArgumentsSources({@ArgumentsSource(HeapifyInputProvider.class)})
  void minPQ_delete_Test(TestData arguments) {
    Integer[] input = arguments.getInput();

    // expected output. it's going to sorted.
    Integer[] expected = Arrays.copyOf(input, input.length);
    Arrays.sort(expected, Integer::compareTo);

    // method/class under test
    PQ<Integer> pq = new PQ<Integer>(input, Comparator.reverseOrder()); // min PQ

    Integer[] actual = new Integer[input.length];
    for (int i = 0; i < input.length; i++) {
      actual[i] = pq.delete();
    }

    Assertions.assertArrayEquals(expected, actual);
  }

  @DisplayName("PQ MAX Peek Test")
  @ParameterizedTest(name = "Running permutation {index}")
  @ArgumentsSources({@ArgumentsSource(HeapifyInputProvider.class)})
  void PQ_peek_Test(TestData arguments) {
    Integer[] input = arguments.getInput();

    // expected output. it's going to be reverse sorted.
    Integer[] expected = Arrays.copyOf(input, input.length);
    Arrays.sort(expected, Comparator.reverseOrder());

    // method/class under test
    PQ<Integer> pq = new PQ<Integer>(input, Integer::compareTo); // MAX PQ

    Assertions.assertEquals(expected[0], pq.peek().get());
  }

  @Test
  @DisplayName("isEmpty should return true after removing all elements")
  void isEmptyTest() {
    // method/class under test
    PQ<Integer> pq = new PQ<Integer>(new Integer[] {5, 4}, Integer::compareTo); // MAX PQ
    Assertions.assertEquals(false, pq.isEmpty());
    pq.delete();
    Assertions.assertEquals(false, pq.isEmpty());
    pq.delete();
    Assertions.assertEquals(true, pq.isEmpty());
  }

  @Test
  @DisplayName("Test size - should return the correct count")
  void test_size() {
    // method/class under test
    PQ<Integer> pq = new PQ<Integer>(16, Integer::compareTo); // MAX PQ
    Assertions.assertEquals(0, pq.size());
    pq.insert(12);
    Assertions.assertEquals(1, pq.size());
    pq.insert(12);
    pq.insert(12);
    pq.delete();
    pq.delete();
    Assertions.assertEquals(1, pq.size());
    pq.delete();
    Assertions.assertEquals(0, pq.size());
  }
}
