package coding.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tests.input.SortInputDuplicatesProvider;
import tests.input.SortInputEvenProvider;
import tests.input.SortInputOddProvider;
import tests.input.TestData;

@RunWith(JUnitPlatform.class)
public class SortTest {

    @DisplayName("ShellSort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void shellSortTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        ShellSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("Selection Sort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void selectionSortTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        SelectionSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("Insertion Sort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void insertionSortTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        InsertionSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("HeapSort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void heapSortTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        HeapSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("QuickSort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void quickSortTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("QuickSort3Way Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSource(SortInputOddProvider.class)
    void quickSort3WayTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort3Way.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }
}
