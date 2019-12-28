package coding.trie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class SuffixTreeTest {

  @Test
  @DisplayName("Test-1 put single key")
  void testPut_SingleKey() {

    // Suffixes:
    //
    //    BANANA
    //     ANANA
    //      NANA
    //       ANA
    //        NA
    //         A

    // Expected suffix tree:
    //
    //    ○
    //    ├── ○ A ([BANANA])
    //    │   └── ○ NA ([BANANA])
    //    │       └── ○ NA ([BANANA])
    //    ├── ○ BANANA ([BANANA])
    //    └── ○ NA ([BANANA])
    //        └── ○ NA ([BANANA])

    String expected =
        "○\n" +
        "├── ○ A ([BANANA])\n" +
        "│   └── ○ NA ([BANANA])\n" +
        "│       └── ○ NA ([BANANA])\n" +
        "├── ○ BANANA ([BANANA])\n" +
        "└── ○ NA ([BANANA])\n" +
        "    └── ○ NA ([BANANA])\n";

    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1); // method under test

    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());
  }

  @Test
  @DisplayName("Test-2 put multiple key")
  void testPut_MultipleKeys() {

    // Suffixes:
    //
    //    BANANA
    //     ANANA
    //      NANA
    //       ANA
    //        NA
    //         A
    //
    //    BANDANA
    //     ANDANA
    //      NDANA
    //       DANA
    //        ANA
    //         NA
    //          A

    // Expected suffix tree:
    //
    //    ○
    //    ├── ○ A ([BANANA, BANDANA])
    //    │   └── ○ N
    //    │       ├── ○ A ([BANANA, BANDANA])
    //    │       │   └── ○ NA ([BANANA])
    //    │       └── ○ DANA ([BANDANA])
    //    ├── ○ BAN
    //    │   ├── ○ ANA ([BANANA])
    //    │   └── ○ DANA ([BANDANA])
    //    ├── ○ DANA ([BANDANA])
    //    └── ○ N
    //        ├── ○ A ([BANANA, BANDANA])
    //        │   └── ○ NA ([BANANA])
    //        └── ○ DANA ([BANDANA])


    String expected =
        "○\n" +
            "├── ○ A ([BANANA, BANDANA])\n" +
            "│   └── ○ N\n" +
            "│       ├── ○ A ([BANANA, BANDANA])\n" +
            "│       │   └── ○ NA ([BANANA])\n" +
            "│       └── ○ DANA ([BANDANA])\n" +
            "├── ○ BAN\n" +
            "│   ├── ○ ANA ([BANANA])\n" +
            "│   └── ○ DANA ([BANDANA])\n" +
            "├── ○ DANA ([BANDANA])\n" +
            "└── ○ N\n" +
            "    ├── ○ A ([BANANA, BANDANA])\n" +
            "    │   └── ○ NA ([BANANA])\n" +
            "    └── ○ DANA ([BANDANA])\n";

    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1); // method under test
    st.put("BANDANA", 2); // method under test

    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());
  }

  @Test
  @DisplayName("Test get operation")
  void testGetValueForExactKey() {
    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1);
    st.put("BANDANA", 2);

    Assertions.assertEquals(Integer.valueOf(1), st.get("BANANA"));
    Assertions.assertEquals(Integer.valueOf(2), st.get("BANDANA"));
    Assertions.assertNull(st.get("BAN"));
    Assertions.assertNull(st.get("ANA"));
  }

  @Test
  @DisplayName("Test of keys operation - 1")
  void test_keys() {
    List<String> expected = Arrays.asList("FOO", "FOOBAR", "FOOBARBAZ");

    Trie<Integer> st = new SuffixTree<>();
    st.put("FOO", 1);
    st.put("FOOBAR", 2);
    st.put("FOOBARBAZ", 3);

    Iterable<String> iterable = st.keys(); // method under test
    List<String> actual =
        StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());

    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  @DisplayName("Test-1 of delete operation")
  void testRemove_RemoveSecondKey() {
    Appendable sb;
    String expected =
        "○\n" +
        "├── ○ A ([BANANA, BANDANA])\n" +
        "│   └── ○ N\n" +
        "│       ├── ○ A ([BANANA, BANDANA])\n" +
        "│       │   └── ○ NA ([BANANA])\n" +
        "│       └── ○ DANA ([BANDANA])\n" +
        "├── ○ BAN\n" +
        "│   ├── ○ ANA ([BANANA])\n" +
        "│   └── ○ DANA ([BANDANA])\n" +
        "├── ○ DANA ([BANDANA])\n" +
        "└── ○ N\n" +
        "    ├── ○ A ([BANANA, BANDANA])\n" +
        "    │   └── ○ NA ([BANANA])\n" +
        "    └── ○ DANA ([BANDANA])\n";

    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1);
    st.put("BANDANA", 2);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());

    st.delete("BANDANA"); // method under test

    expected =
        "○\n" +
        "├── ○ A ([BANANA])\n" +
        "│   └── ○ NA ([BANANA])\n" +
        "│       └── ○ NA ([BANANA])\n" +
        "├── ○ BANANA ([BANANA])\n" +
        "└── ○ NA ([BANANA])\n" +
        "    └── ○ NA ([BANANA])\n";

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());

    Assertions.assertNull(st.get("BANDANA"));
  }

  @Test
  @DisplayName("Test-2 of delete operation")
  void testRemove_RemoveFirstKey() {
    Appendable sb;

    String expected =
        "○\n" +
        "├── ○ A ([BANANA, BANDANA])\n" +
        "│   └── ○ N\n" +
        "│       ├── ○ A ([BANANA, BANDANA])\n" +
        "│       │   └── ○ NA ([BANANA])\n" +
        "│       └── ○ DANA ([BANDANA])\n" +
        "├── ○ BAN\n" +
        "│   ├── ○ ANA ([BANANA])\n" +
        "│   └── ○ DANA ([BANDANA])\n" +
        "├── ○ DANA ([BANDANA])\n" +
        "└── ○ N\n" +
        "    ├── ○ A ([BANANA, BANDANA])\n" +
        "    │   └── ○ NA ([BANANA])\n" +
        "    └── ○ DANA ([BANDANA])\n";


    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1);
    st.put("BANDANA", 2);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());

    st.delete("BANANA"); // method under test

    expected =
        "○\n" +
        "├── ○ A ([BANDANA])\n" +
        "│   └── ○ N\n" +
        "│       ├── ○ A ([BANDANA])\n" +
        "│       └── ○ DANA ([BANDANA])\n" +
        "├── ○ BANDANA ([BANDANA])\n" +
        "├── ○ DANA ([BANDANA])\n" +
        "└── ○ N\n" +
        "    ├── ○ A ([BANDANA])\n" +
        "    └── ○ DANA ([BANDANA])\n";

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());
    Assertions.assertNull(st.get("BANANA"));
  }

  @Test
  @DisplayName("Test-3 of delete operation")
  void testRemove_RemoveNonExistentKey() {
    Appendable sb;
    String expected =
        "○\n" +
        "├── ○ A ([BANANA, BANDANA])\n" +
        "│   └── ○ N\n" +
        "│       ├── ○ A ([BANANA, BANDANA])\n" +
        "│       │   └── ○ NA ([BANANA])\n" +
        "│       └── ○ DANA ([BANDANA])\n" +
        "├── ○ BAN\n" +
        "│   ├── ○ ANA ([BANANA])\n" +
        "│   └── ○ DANA ([BANDANA])\n" +
        "├── ○ DANA ([BANDANA])\n" +
        "└── ○ N\n" +
        "    ├── ○ A ([BANANA, BANDANA])\n" +
        "    │   └── ○ NA ([BANANA])\n" +
        "    └── ○ DANA ([BANDANA])\n";


    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1);
    st.put("BANDANA", 2);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());

    st.delete("APPLE"); // method under test

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expected, sb.toString());
  }

  @Test
  @DisplayName("Test of keysWithPrefix Operation")
  void test_keysWithPrefix() {
    List<String> actual;
    Iterable<String> iterable;

    SuffixTree<Integer> st = new SuffixTree<>();
    st.put("BANANA", 1);
    st.put("BANDANA", 2);

    iterable = st.keysWithPrefix("ANAN");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"BANANA"},actual.toArray());

    iterable = st.keysWithPrefix("DA");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"BANDANA"},actual.toArray());

    iterable = st.keysWithPrefix("AN");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"BANANA", "BANDANA"},actual.toArray());

    iterable = st.keysWithPrefix("BAN");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"BANANA", "BANDANA"},actual.toArray());

    iterable = st.keysWithPrefix("ANA");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"BANANA", "BANDANA"},actual.toArray());

    iterable = st.keysWithPrefix("APPLE");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{},actual.toArray());
  }

}
