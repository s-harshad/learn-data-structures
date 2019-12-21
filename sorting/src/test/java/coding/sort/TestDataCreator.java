package coding.sort;

import java.util.Arrays;
import java.util.Collection;

final class TestDataCreator {

    public static final Collection<TestData> getData_SortTest_OddElements() {
        final Integer[] expectedOutput = {1, 2, 3, 4, 5};
        return collectionOfAllPermutationsOfOddElements(expectedOutput);
    }

    public static final Collection<TestData> getData_SortTest_EvenElements() {
        final Integer[] expectedOutput = {1, 2, 3, 4};
        return collectionOfAllPermutationsOfEvenElements(expectedOutput);
    }

    public static final Collection<TestData> getData_SortTest_DuplicateElements() {
        final Integer[] expectedOutput = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4};
        return collectionWithDuplicateData(expectedOutput);
    }

    public static final Collection<TestData> getData_HeapifyTest_DuplicateElements() {
        return collectionWithDuplicateData(null);
    }

    public static final Collection<TestData> getData_HeapifyTest_OddElements() {
        return collectionOfAllPermutationsOfOddElements(null);
    }

    public static final Collection<TestData> getData_HeapifyTest_EvenElements() {
        return collectionOfAllPermutationsOfEvenElements(null);
    }

    private TestDataCreator() {};

    private static final Collection<TestData> collectionOfAllPermutationsOfOddElements(Integer[] expectedOutput) {

        return Arrays.asList(
                new TestData(new Integer[]{1, 2, 3, 4, 5}, expectedOutput),
                new TestData(new Integer[]{2, 1, 3, 4, 5}, expectedOutput),
                new TestData(new Integer[]{3, 1, 2, 4, 5}, expectedOutput),
                new TestData(new Integer[]{1, 3, 2, 4, 5}, expectedOutput),
                new TestData(new Integer[]{2, 3, 1, 4, 5}, expectedOutput),
                new TestData(new Integer[]{3, 2, 1, 4, 5}, expectedOutput),
                new TestData(new Integer[]{3, 2, 4, 1, 5}, expectedOutput),
                new TestData(new Integer[]{2, 3, 4, 1, 5}, expectedOutput),
                new TestData(new Integer[]{4, 3, 2, 1, 5}, expectedOutput),
                new TestData(new Integer[]{3, 4, 2, 1, 5}, expectedOutput),
                new TestData(new Integer[]{2, 4, 3, 1, 5}, expectedOutput),
                new TestData(new Integer[]{4, 2, 3, 1, 5}, expectedOutput),
                new TestData(new Integer[]{4, 1, 3, 2, 5}, expectedOutput),
                new TestData(new Integer[]{1, 4, 3, 2, 5}, expectedOutput),
                new TestData(new Integer[]{3, 4, 1, 2, 5}, expectedOutput),
                new TestData(new Integer[]{4, 3, 1, 2, 5}, expectedOutput),
                new TestData(new Integer[]{1, 3, 4, 2, 5}, expectedOutput),
                new TestData(new Integer[]{3, 1, 4, 2, 5}, expectedOutput),
                new TestData(new Integer[]{2, 1, 4, 3, 5}, expectedOutput),
                new TestData(new Integer[]{1, 2, 4, 3, 5}, expectedOutput),
                new TestData(new Integer[]{4, 2, 1, 3, 5}, expectedOutput),
                new TestData(new Integer[]{2, 4, 1, 3, 5}, expectedOutput),
                new TestData(new Integer[]{1, 4, 2, 3, 5}, expectedOutput),
                new TestData(new Integer[]{4, 1, 2, 3, 5}, expectedOutput),
                new TestData(new Integer[]{5, 1, 2, 3, 4}, expectedOutput),
                new TestData(new Integer[]{1, 5, 2, 3, 4}, expectedOutput),
                new TestData(new Integer[]{2, 5, 1, 3, 4}, expectedOutput),
                new TestData(new Integer[]{5, 2, 1, 3, 4}, expectedOutput),
                new TestData(new Integer[]{1, 2, 5, 3, 4}, expectedOutput),
                new TestData(new Integer[]{2, 1, 5, 3, 4}, expectedOutput),
                new TestData(new Integer[]{2, 1, 3, 5, 4}, expectedOutput),
                new TestData(new Integer[]{1, 2, 3, 5, 4}, expectedOutput),
                new TestData(new Integer[]{3, 2, 1, 5, 4}, expectedOutput),
                new TestData(new Integer[]{2, 3, 1, 5, 4}, expectedOutput),
                new TestData(new Integer[]{1, 3, 2, 5, 4}, expectedOutput),
                new TestData(new Integer[]{3, 1, 2, 5, 4}, expectedOutput),
                new TestData(new Integer[]{3, 5, 2, 1, 4}, expectedOutput),
                new TestData(new Integer[]{5, 3, 2, 1, 4}, expectedOutput),
                new TestData(new Integer[]{2, 3, 5, 1, 4}, expectedOutput),
                new TestData(new Integer[]{3, 2, 5, 1, 4}, expectedOutput),
                new TestData(new Integer[]{5, 2, 3, 1, 4}, expectedOutput),
                new TestData(new Integer[]{2, 5, 3, 1, 4}, expectedOutput),
                new TestData(new Integer[]{1, 5, 3, 2, 4}, expectedOutput),
                new TestData(new Integer[]{5, 1, 3, 2, 4}, expectedOutput),
                new TestData(new Integer[]{3, 1, 5, 2, 4}, expectedOutput),
                new TestData(new Integer[]{1, 3, 5, 2, 4}, expectedOutput),
                new TestData(new Integer[]{5, 3, 1, 2, 4}, expectedOutput),
                new TestData(new Integer[]{3, 5, 1, 2, 4}, expectedOutput),
                new TestData(new Integer[]{4, 5, 1, 2, 3}, expectedOutput),
                new TestData(new Integer[]{5, 4, 1, 2, 3}, expectedOutput),
                new TestData(new Integer[]{1, 4, 5, 2, 3}, expectedOutput),
                new TestData(new Integer[]{4, 1, 5, 2, 3}, expectedOutput),
                new TestData(new Integer[]{5, 1, 4, 2, 3}, expectedOutput),
                new TestData(new Integer[]{1, 5, 4, 2, 3}, expectedOutput),
                new TestData(new Integer[]{1, 5, 2, 4, 3}, expectedOutput),
                new TestData(new Integer[]{5, 1, 2, 4, 3}, expectedOutput),
                new TestData(new Integer[]{2, 1, 5, 4, 3}, expectedOutput),
                new TestData(new Integer[]{1, 2, 5, 4, 3}, expectedOutput),
                new TestData(new Integer[]{5, 2, 1, 4, 3}, expectedOutput),
                new TestData(new Integer[]{2, 5, 1, 4, 3}, expectedOutput),
                new TestData(new Integer[]{2, 4, 1, 5, 3}, expectedOutput),
                new TestData(new Integer[]{4, 2, 1, 5, 3}, expectedOutput),
                new TestData(new Integer[]{1, 2, 4, 5, 3}, expectedOutput),
                new TestData(new Integer[]{2, 1, 4, 5, 3}, expectedOutput),
                new TestData(new Integer[]{4, 1, 2, 5, 3}, expectedOutput),
                new TestData(new Integer[]{1, 4, 2, 5, 3}, expectedOutput),
                new TestData(new Integer[]{5, 4, 2, 1, 3}, expectedOutput),
                new TestData(new Integer[]{4, 5, 2, 1, 3}, expectedOutput),
                new TestData(new Integer[]{2, 5, 4, 1, 3}, expectedOutput),
                new TestData(new Integer[]{5, 2, 4, 1, 3}, expectedOutput),
                new TestData(new Integer[]{4, 2, 5, 1, 3}, expectedOutput),
                new TestData(new Integer[]{2, 4, 5, 1, 3}, expectedOutput),
                new TestData(new Integer[]{3, 4, 5, 1, 2}, expectedOutput),
                new TestData(new Integer[]{4, 3, 5, 1, 2}, expectedOutput),
                new TestData(new Integer[]{5, 3, 4, 1, 2}, expectedOutput),
                new TestData(new Integer[]{3, 5, 4, 1, 2}, expectedOutput),
                new TestData(new Integer[]{4, 5, 3, 1, 2}, expectedOutput),
                new TestData(new Integer[]{5, 4, 3, 1, 2}, expectedOutput),
                new TestData(new Integer[]{5, 4, 1, 3, 2}, expectedOutput),
                new TestData(new Integer[]{4, 5, 1, 3, 2}, expectedOutput),
                new TestData(new Integer[]{1, 5, 4, 3, 2}, expectedOutput),
                new TestData(new Integer[]{5, 1, 4, 3, 2}, expectedOutput),
                new TestData(new Integer[]{4, 1, 5, 3, 2}, expectedOutput),
                new TestData(new Integer[]{1, 4, 5, 3, 2}, expectedOutput),
                new TestData(new Integer[]{1, 3, 5, 4, 2}, expectedOutput),
                new TestData(new Integer[]{3, 1, 5, 4, 2}, expectedOutput),
                new TestData(new Integer[]{5, 1, 3, 4, 2}, expectedOutput),
                new TestData(new Integer[]{1, 5, 3, 4, 2}, expectedOutput),
                new TestData(new Integer[]{3, 5, 1, 4, 2}, expectedOutput),
                new TestData(new Integer[]{5, 3, 1, 4, 2}, expectedOutput),
                new TestData(new Integer[]{4, 3, 1, 5, 2}, expectedOutput),
                new TestData(new Integer[]{3, 4, 1, 5, 2}, expectedOutput),
                new TestData(new Integer[]{1, 4, 3, 5, 2}, expectedOutput),
                new TestData(new Integer[]{4, 1, 3, 5, 2}, expectedOutput),
                new TestData(new Integer[]{3, 1, 4, 5, 2}, expectedOutput),
                new TestData(new Integer[]{1, 3, 4, 5, 2}, expectedOutput),
                new TestData(new Integer[]{2, 3, 4, 5, 1}, expectedOutput),
                new TestData(new Integer[]{3, 2, 4, 5, 1}, expectedOutput),
                new TestData(new Integer[]{4, 2, 3, 5, 1}, expectedOutput),
                new TestData(new Integer[]{2, 4, 3, 5, 1}, expectedOutput),
                new TestData(new Integer[]{3, 4, 2, 5, 1}, expectedOutput),
                new TestData(new Integer[]{4, 3, 2, 5, 1}, expectedOutput),
                new TestData(new Integer[]{4, 3, 5, 2, 1}, expectedOutput),
                new TestData(new Integer[]{3, 4, 5, 2, 1}, expectedOutput),
                new TestData(new Integer[]{5, 4, 3, 2, 1}, expectedOutput),
                new TestData(new Integer[]{4, 5, 3, 2, 1}, expectedOutput),
                new TestData(new Integer[]{3, 5, 4, 2, 1}, expectedOutput),
                new TestData(new Integer[]{5, 3, 4, 2, 1}, expectedOutput),
                new TestData(new Integer[]{5, 2, 4, 3, 1}, expectedOutput),
                new TestData(new Integer[]{2, 5, 4, 3, 1}, expectedOutput),
                new TestData(new Integer[]{4, 5, 2, 3, 1}, expectedOutput),
                new TestData(new Integer[]{5, 4, 2, 3, 1}, expectedOutput),
                new TestData(new Integer[]{2, 4, 5, 3, 1}, expectedOutput),
                new TestData(new Integer[]{4, 2, 5, 3, 1}, expectedOutput),
                new TestData(new Integer[]{3, 2, 5, 4, 1}, expectedOutput),
                new TestData(new Integer[]{2, 3, 5, 4, 1}, expectedOutput),
                new TestData(new Integer[]{5, 3, 2, 4, 1}, expectedOutput),
                new TestData(new Integer[]{3, 5, 2, 4, 1}, expectedOutput),
                new TestData(new Integer[]{2, 5, 3, 4, 1}, expectedOutput),
                new TestData(new Integer[]{5, 2, 3, 4, 1}, expectedOutput)
        );
    }

    private static final Collection<TestData> collectionWithDuplicateData(Integer[] expectedOutput) {
        return Arrays.asList(
                new TestData(new Integer[]{1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,}, expectedOutput),
                new TestData(new Integer[]{2, 1, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,}, expectedOutput),
                new TestData(new Integer[]{3, 1, 2, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,}, expectedOutput),
                new TestData(new Integer[]{4, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4,}, expectedOutput));
    }

    private static final Collection<TestData> collectionOfAllPermutationsOfEvenElements(Integer[] expectedOutput) {
        return Arrays.asList(
                new TestData(new Integer[]{1, 2, 3, 4}, expectedOutput),
                new TestData(new Integer[]{2, 1, 3, 4}, expectedOutput),
                new TestData(new Integer[]{3, 1, 2, 4}, expectedOutput),
                new TestData(new Integer[]{1, 3, 2, 4}, expectedOutput),
                new TestData(new Integer[]{2, 3, 1, 4}, expectedOutput),
                new TestData(new Integer[]{3, 2, 1, 4}, expectedOutput),
                new TestData(new Integer[]{3, 2, 4, 1}, expectedOutput),
                new TestData(new Integer[]{2, 3, 4, 1}, expectedOutput),
                new TestData(new Integer[]{4, 3, 2, 1}, expectedOutput),
                new TestData(new Integer[]{3, 4, 2, 1}, expectedOutput),
                new TestData(new Integer[]{2, 4, 3, 1}, expectedOutput),
                new TestData(new Integer[]{4, 2, 3, 1}, expectedOutput),
                new TestData(new Integer[]{4, 1, 3, 2}, expectedOutput),
                new TestData(new Integer[]{1, 4, 3, 2}, expectedOutput),
                new TestData(new Integer[]{3, 4, 1, 2}, expectedOutput),
                new TestData(new Integer[]{4, 3, 1, 2}, expectedOutput),
                new TestData(new Integer[]{1, 3, 4, 2}, expectedOutput),
                new TestData(new Integer[]{3, 1, 4, 2}, expectedOutput),
                new TestData(new Integer[]{2, 1, 4, 3}, expectedOutput),
                new TestData(new Integer[]{1, 2, 4, 3}, expectedOutput),
                new TestData(new Integer[]{4, 2, 1, 3}, expectedOutput),
                new TestData(new Integer[]{2, 4, 1, 3}, expectedOutput),
                new TestData(new Integer[]{1, 4, 2, 3}, expectedOutput),
                new TestData(new Integer[]{4, 1, 2, 3}, expectedOutput));
    }


}
