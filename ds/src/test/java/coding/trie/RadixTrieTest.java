package coding.trie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class RadixTrieTest {

  @Test
  @DisplayName("Test of operation largestPrefixLength")
  void test1() {
    System.out.println("RadixTrie.largestPrefixLength(\"cat\", \"car\") = " + RadixTrie.largestPrefixLength("cat", "car"));
    System.out.println("RadixTrie.largestPrefixLength(\"car\", \"cargo\") = " + RadixTrie.largestPrefixLength("car", "cargo"));
    System.out.println("RadixTrie.largestPrefixLength(\"abc\", \"def\") = " + RadixTrie.largestPrefixLength("abc", "def"));
  }
  
}
