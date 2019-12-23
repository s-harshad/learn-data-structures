package coding.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class HeapifyInputProvider implements ArgumentsProvider {
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
      throws Exception {

    return Stream.concat(
            Stream.concat(
                TestDataCreator.getData_HeapifyTest_EvenElements().stream(),
                TestDataCreator.getData_HeapifyTest_DuplicateElements().stream()),
            TestDataCreator.getData_HeapifyTest_OddElements().stream())
        .map(testData -> Arguments.of(testData));
  }
}
