package coding.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tests.input.TestData;
import tests.input.SortInputDuplicatesProvider;
import tests.input.SortInputOddProvider;
import tests.input.SortInputEvenProvider;

@RunWith(JUnitPlatform.class)
public class QuickSortTest {

    @DisplayName("QuickSort Test - Odd Number of Elements")
    @ParameterizedTest(name = "Running permutation {index} of 5!")
    @ArgumentsSource(SortInputOddProvider.class)
    void sortDistinctArrayWithOddElements(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("QuickSort Test - Even Number of Elements")
    @ParameterizedTest(name = "Running permutation {index} of 4!")
    @ArgumentsSource(SortInputEvenProvider.class)
    void sortDistinctArrayWithEvenElements(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("QuickSort Test - Collection with duplicates")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSource(SortInputDuplicatesProvider.class)
    void sortArrayWithDuplicates(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

}
