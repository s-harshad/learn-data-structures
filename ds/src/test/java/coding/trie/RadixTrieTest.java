package coding.trie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
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
public class RadixTrieTest {

  @Test
  @DisplayName("Test of put - 1")
  void testPut_AddToRoot() {
    String expectedTree = "○\n" +
                          "└── ○ A (1)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("A", 1); // method under test.
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 2")
  void testPut_ChildNodeSorting() {
    String expectedTree =
        "○\n" +
        "├── ○ A (2)\n" +
        "└── ○ B (1)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("B", 1); // method under test.
    st.put("A", 2); // method under test.
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 3")
  void testPut_AppendChild() {
    String expectedTree =
        "○\n" +
            "└── ○ FOO (1)\n" +
            "    └── ○ BAR (2)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1); // method under test.
    st.put("FOOBAR", 2); // method under test.
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 4")
  void testPut_SplitEdge() {
    String expectedTree =
        "○\n" +
        "└── ○ FOO (2)\n" +
        "    └── ○ BAR (1)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOOBAR", 1); // method under test.
    st.put("FOO", 2); // method under test.
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 5")
  void testPut_SplitWithImplicitNode() {
    String expectedTree =
        "○\n" +
            "└── ○ FOO\n" + // We never explicitly inserted FOO
            "    ├── ○ BAR (1)\n" +
            "    └── ○ D (2)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOOBAR", 1); // method under test.
    st.put("FOOD", 2); // method under test.
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 6")
  void testPut_SplitAndMove() {
    String expectedTree =
        "○\n" +
        "└── ○ T\n" +             // implicit node added automatically
        "    ├── ○ E\n" +         // implicit node added automatically
        "    │   ├── ○ AM (2)\n" +
        "    │   └── ○ ST (1)\n" +
        "    └── ○ OAST (3)\n";
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("TEST", 1);
    st.put("TEAM", 2);
    st.put("TOAST", 3);
    Appendable sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTree, sb.toString());
  }

  @Test
  @DisplayName("Test of put - 7")
  void testPut_OverwriteValue() {
    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("TEST", 1);
    st.put("TEAM", 2);
    st.put("TOAST", 3);
    assertEquals(Integer.valueOf(1), st.get("TEST"));
    st.put("TEST", 4);
    assertEquals(Integer.valueOf(4), st.get("TEST"));
  }

  @Test
  @DisplayName("Test of get")
  void testGet() {
    Trie<Integer> st = new RadixTrie<>();
    st.put("TEST", 1);
    st.put("TEAM", 2);
    st.put("TOAST", 3);

    //    ○
    //    └── ○ T               // implicit node added automatically
    //        ├── ○ E           // implicit node added automatically
    //        │   ├── ○ AM (2)
    //        │   └── ○ ST (1)
    //        └── ○ OAST (3)

    assertEquals(Integer.valueOf(1), st.get("TEST"));
    assertEquals(Integer.valueOf(2), st.get("TEAM"));
    assertEquals(Integer.valueOf(3), st.get("TOAST"));
    assertNull(st.get("T"));
    assertNull(st.get("TE"));
    assertNull(st.get("E")); // sanity check, no such edge from root
    assertNull(st.get("")); // sanity check, root never has a value
  }

  @Test
  @DisplayName("Test of delete - 1")
  void testdelete_ExactlyOneChildEdge() {

    String expectedTreeBeforeDelete =
        "○\n" +
        "└── ○ FOO (1)\n" +
        "    └── ○ BAR (2)\n" +
        "        └── ○ BAZ (3)\n";

    String expectedTreeAfterDelete =
        "○\n" +
        "└── ○ FOOBAR (2)\n" + // Edges FOO and BAR merged,
        "    └── ○ BAZ (3)\n"; // and the value and child edges from BAR also copied into merged node

    Appendable sb;

    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1);
    st.put("FOOBAR", 2);
    st.put("FOOBARBAZ", 3);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeBeforeDelete, sb.toString());
    st.delete("FOO"); // method under test
    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeAfterDelete, sb.toString());
  }

  @Test
  @DisplayName("Test of delete - 2")
  void testDelete_MoreThanOneChildEdge() {

    String expectedTreeBeforeDelete =
        "○\n" +
        "└── ○ FOO (1)\n" +
        "    ├── ○ BAR (2)\n" +
        "    └── ○ D (3)\n";

    String expectedTreeAfterDelete =
        "○\n" +
        "└── ○ FOO\n" +  // value removed from FOO, but node needs to stay (as implicit node)
        "    ├── ○ BAR (2)\n" +
        "    └── ○ D (3)\n";

    Appendable sb;

    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1);
    st.put("FOOBAR", 2);
    st.put("FOOD", 3);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeBeforeDelete, sb.toString());
    st.delete("FOO"); // method under test
    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeAfterDelete, sb.toString());
  }

  @Test
  @DisplayName("Test of delete - 3")
  void testDelete_ZeroChildEdges_DirectChildOfRoot() {

    String expectedTreeBeforeDelete =
        "○\n" +
        "├── ○ BAR (2)\n" +
        "└── ○ FOO (1)\n";

    String expectedTreeAfterDelete =
        "○\n" +  // FOO removed, which involved recreating the root to change its child edges
        "└── ○ BAR (2)\n";

    Appendable sb;

    RadixTrie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1);
    st.put("BAR", 2);

    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeBeforeDelete, sb.toString());
    st.delete("FOO"); // method under test
    sb = new StringBuilder();
    st.prettyPrint(sb);
    Assertions.assertEquals(expectedTreeAfterDelete, sb.toString());

  }

  @Test
  @DisplayName("Test of keys operation - 1")
  void test_keys() {
    List<String> expected = Arrays.asList("FOO", "FOOBAR", "FOOBARBAZ");

    Trie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1);
    st.put("FOOBAR", 2);
    st.put("FOOBARBAZ", 3);

    List<String> actual = new ArrayList<>(3);
    for (String key : st.keys()) { // st.keys() method under test
      actual.add(key);
    }

    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  @DisplayName("Test of keys operation - 2")
  void test_keys_2() {
    List<String> expected = Arrays.asList("FOO", "FOOBAR", "FOOD");

    Trie<Integer> st = new RadixTrie<>();
    st.put("FOO", 1);
    st.put("FOOBAR", 2);
    st.put("FOOD", 3);

    List<String> actual = new ArrayList<>(3);
    for (String key : st.keys()) { // st.keys() method under test
      actual.add(key);
    }

    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  @DisplayName("Test keys with prefix operation")
  void test_keysWithPrefix() {

    List<String> actual;
    Iterable<String> iterable;

    Trie<Integer> st = new RadixTrie<>();
    st.put("TEST", 1);
    st.put("TEAM", 2);
    st.put("TOAST", 3);
    st.put("TEA", 4);
    st.put("COFFEE", 5);

    //    ○
    //    ├── ○ COFFEE (5)
    //    └── ○ T
    //        ├── ○ E
    //        │   ├── ○ A (4)
    //        │   │   └── ○ M (2)
    //        │   └── ○ ST (1)
    //        └── ○ OAST (3)

    iterable = st.keysWithPrefix("C");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"COFFEE"},actual.toArray());

    iterable = st.keysWithPrefix("COFFEE");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"COFFEE"},actual.toArray());

    iterable = st.keysWithPrefix("COFFEES");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{},actual.toArray());

    iterable = st.keysWithPrefix("T");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"TEA", "TEAM", "TEST", "TOAST"},actual.toArray());

    iterable = st.keysWithPrefix("TE");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"TEA", "TEAM", "TEST"},actual.toArray());

    iterable = st.keysWithPrefix("TEA");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"TEA", "TEAM"},actual.toArray());

    iterable = st.keysWithPrefix("TO");
    actual = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    Assertions.assertArrayEquals(new String[]{"TOAST"},actual.toArray());

  }

  @Test
  @DisplayName(("test of longestPrefixOf operation"))
  void test7() {
    Trie<String> st = new RadixTrie<>();
    st.put("cat", "nope");
    st.put("car", "kia");
    st.put("computer", "desktop");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");
    st.put("dummy", "dummy-value");

    Assertions.assertEquals("car", st.longestPrefixOf("car"));
    Assertions.assertEquals("car", st.longestPrefixOf("carg"));
    Assertions.assertEquals("cargo", st.longestPrefixOf("cargo"));
  }

}
