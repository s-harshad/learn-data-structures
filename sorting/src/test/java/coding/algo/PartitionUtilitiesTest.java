package coding.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tests.input.TestData;
import tests.input.SortInputDuplicatesProvider;
import tests.input.SortInputEvenProvider;
import tests.input.SortInputOddProvider;


@RunWith(JUnitPlatform.class)
public class PartitionUtilitiesTest {


    @DisplayName("PartitionUtilities Test")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputOddProvider.class)
    })
    void testPartitionOfAnArray(TestData arguments) {
        //make a copy of the array
        Integer[] inputCopy = arguments.getInput();

        Integer[] input = arguments.getInput();
        // method under test
        int indexOfPartitionedPosition = PartitionUtilities.partition(input, 0, input.length - 1);

        // since our data is numbers starting from 1
        // in the final sorted order 1 will be at index 0, 2 will be at index 1 and so on.
        Assertions.assertEquals(indexOfPartitionedPosition, inputCopy[0] - 1);
    }

    @DisplayName("Partition Utilities 3 Way Test Distinct Items")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputEvenProvider.class),
            @ArgumentsSource(SortInputOddProvider.class),
    })
    void testPartitionOfAnArray3WayDistinctElements(TestData arguments) {
        //make a copy of the array
        Integer[] inputCopy = arguments.getInput();

        // since our data is unique elements
        // when we partition, start and end indices are going to be the same
        int[] expectedIndices = new int[]{inputCopy[0] - 1, inputCopy[0] - 1};

        Integer[] input = arguments.getInput();
        // method under test
        int[] indexOfPartitionedPosition = PartitionUtilities.partitionDuplicateEntriesInArray(input, 0, input.length - 1);

        // since our data is numbers starting from 1
        // in the final sorted order 1 will be at index 0, 2 will be at index 1 and so on.
        Assertions.assertArrayEquals(indexOfPartitionedPosition, expectedIndices);
    }

    @DisplayName("Partition Utilities 3 Way Test Duplicate Items")
    @ParameterizedTest(name = "Running permutation {index}")
    @ArgumentsSources({
            @ArgumentsSource(SortInputDuplicatesProvider.class)
    })
    void testPartitionOfAnArray3WayDuplicateElements(TestData arguments) {
        //make a copy of the array
        Integer[] inputCopy = arguments.getInput();
        int element0 = inputCopy[0];

        // element 1 is duplicated 5 times.
        // element 2 is duplicated 5 times... and so on
        // so the startIndex for partition becomes
        int startIndex = (element0 - 1) * 5;
        // end index is just adding 4 more elements.
        int endIndex = startIndex + 4;
        int[] expectedIndices = new int[]{startIndex, endIndex};

        Integer[] input = arguments.getInput();
        // method under test
        int[] indexOfPartitionedPosition = PartitionUtilities.partitionDuplicateEntriesInArray(input, 0, input.length - 1);

        // since our data is numbers starting from 1
        // in the final sorted order 1 will be at index 0, 2 will be at index 1 and so on.
        Assertions.assertArrayEquals(indexOfPartitionedPosition, expectedIndices);
    }

}
