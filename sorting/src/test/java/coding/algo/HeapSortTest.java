package coding.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tests.input.*;

import java.util.Arrays;
import java.util.Comparator;

@RunWith(JUnitPlatform.class)
public class HeapSortTest {

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

}
