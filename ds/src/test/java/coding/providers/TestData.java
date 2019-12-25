package coding.providers;

import java.util.Arrays;

public final class TestData {

  final Integer[] input;
  final Integer[] expectedOutput;

  public TestData(Integer[] input, Integer[] expectedOutput) {
    this.input = input;
    this.expectedOutput = expectedOutput;
  }

  public Integer[] getInput() {
    return Arrays.copyOf(input, input.length);
  }

  public Integer[] getExpectedOutput() {
    return Arrays.copyOf(expectedOutput, expectedOutput.length);
  }
}
