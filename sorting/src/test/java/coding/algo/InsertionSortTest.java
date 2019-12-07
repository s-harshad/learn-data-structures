package coding.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import tests.input.SortData;
import tests.input.SortInputDuplicatesProvider;
import tests.input.SortInputEvenProvider;
import tests.input.SortInputOddProvider;

public class InsertionSortTest {

    @DisplayName("Insertion Sort Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void sort(SortData arguments) {
        Integer[] input = arguments.getInput();
        InsertionSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }
}
