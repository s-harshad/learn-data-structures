package tests.input;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class SortInputEvenProvider implements ArgumentsProvider {

    private final Integer[] expectedOutput = {1, 2, 3, 4};

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        return Stream.of(
                new SortData(new Integer[]{1, 2, 3, 4}, expectedOutput),
                new SortData(new Integer[]{2, 1, 3, 4}, expectedOutput),
                new SortData(new Integer[]{3, 1, 2, 4}, expectedOutput),
                new SortData(new Integer[]{1, 3, 2, 4}, expectedOutput),
                new SortData(new Integer[]{2, 3, 1, 4}, expectedOutput),
                new SortData(new Integer[]{3, 2, 1, 4}, expectedOutput),
                new SortData(new Integer[]{3, 2, 4, 1}, expectedOutput),
                new SortData(new Integer[]{2, 3, 4, 1}, expectedOutput),
                new SortData(new Integer[]{4, 3, 2, 1}, expectedOutput),
                new SortData(new Integer[]{3, 4, 2, 1}, expectedOutput),
                new SortData(new Integer[]{2, 4, 3, 1}, expectedOutput),
                new SortData(new Integer[]{4, 2, 3, 1}, expectedOutput),
                new SortData(new Integer[]{4, 1, 3, 2}, expectedOutput),
                new SortData(new Integer[]{1, 4, 3, 2}, expectedOutput),
                new SortData(new Integer[]{3, 4, 1, 2}, expectedOutput),
                new SortData(new Integer[]{4, 3, 1, 2}, expectedOutput),
                new SortData(new Integer[]{1, 3, 4, 2}, expectedOutput),
                new SortData(new Integer[]{3, 1, 4, 2}, expectedOutput),
                new SortData(new Integer[]{2, 1, 4, 3}, expectedOutput),
                new SortData(new Integer[]{1, 2, 4, 3}, expectedOutput),
                new SortData(new Integer[]{4, 2, 1, 3}, expectedOutput),
                new SortData(new Integer[]{2, 4, 1, 3}, expectedOutput),
                new SortData(new Integer[]{1, 4, 2, 3}, expectedOutput),
                new SortData(new Integer[]{4, 1, 2, 3}, expectedOutput)
        ).map(Arguments::of);
    }
}
