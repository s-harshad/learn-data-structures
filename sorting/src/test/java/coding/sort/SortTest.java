package coding.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

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
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void quickSort3WayTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        QuickSort3Way.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("MergeSort Bottom Up Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void mergeSortBottomUpTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        MergeSortBottomUp.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("MergeSort Top Down Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void mergeSortTopDownTest(TestData arguments) {
        Integer[] input = arguments.getInput();
        MergeSortTopDown.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }

    @DisplayName("LSD Sort Integer Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputOddProvider.class),
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void LSDSort_Integer_Test(TestData arguments) {
        Integer[] input = arguments.getInput();
        LSDSort.sort(input);
        Assertions.assertArrayEquals(arguments.getExpectedOutput(), input);
    }


    @DisplayName("LSD Sort String Test")
    @Test
    void LSDSort_Strings_Test() {
        String[] input = new String[]{"bpeg", "xsdx", "mhjs", "nzks", "gxwy", "pbqu", "ikfn", "hxwt", "kzmc", "oqpv", "xive", "xkov", "jiia", "jkgq", "ipcz", "kmtj", "hlrm", "godj", "vklm", "ajyg", "uyxh", "ytrl", "tkdo", "jwxq", "awka"};
        String[] expectedOutput = new String[]{"ajyg", "awka", "bpeg", "godj", "gxwy", "hlrm", "hxwt", "ikfn", "ipcz", "jiia", "jkgq", "jwxq", "kmtj", "kzmc", "mhjs", "nzks", "oqpv", "pbqu", "tkdo", "uyxh", "vklm", "xive", "xkov", "xsdx", "ytrl"};
        LSDSort.sort(input);
        Assertions.assertArrayEquals(expectedOutput, input);
    }

    @DisplayName("MSD Sort String Test")
    @Test
    void MSDSort_Strings_Test() {
        String[] input = new String[]{"bpeg", "xsdx", "mhjs", "nzks", "gxwy", "pbqu", "ikfn", "hxwt", "kzmc", "oqpv", "xive", "xkov", "jiia", "jkgq", "ipcz", "kmtj", "hlrm", "godj", "vklm", "ajyg", "uyxh", "ytrl", "tkdo", "jwxq", "awka"};
        String[] expectedOutput = new String[]{"ajyg", "awka", "bpeg", "godj", "gxwy", "hlrm", "hxwt", "ikfn", "ipcz", "jiia", "jkgq", "jwxq", "kmtj", "kzmc", "mhjs", "nzks", "oqpv", "pbqu", "tkdo", "uyxh", "vklm", "xive", "xkov", "xsdx", "ytrl"};
        MSDSort.sort(input);
        Assertions.assertArrayEquals(expectedOutput, input);
    }

}
