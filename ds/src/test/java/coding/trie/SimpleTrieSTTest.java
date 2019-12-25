package coding.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class SimpleTrieSTTest {

  @Test
  @DisplayName("should be empty by default")
  void test1() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    Assertions.assertEquals(0, st.size());
    Assertions.assertTrue(st.isEmpty());
  }

  @Test
  @DisplayName("test of retrieval operations")
  void test2() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    st.put("car", "kia");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");
    st.put("cat", "nope");

    Assertions.assertEquals("kia", st.get("car"));
    Assertions.assertEquals("nope", st.get("cat"));
    Assertions.assertNull(st.get("carg"));
    Assertions.assertNull(st.get("ca"));
  }

  @Test
  @DisplayName("test of delete operations")
  void test3() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    st.put("car", "kia");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");
    st.put("cat", "nope");

    Assertions.assertEquals(4, st.size());

    st.delete("ca"); // delete non existing key
    Assertions.assertEquals(4, st.size());
    st.delete("cat");
    Assertions.assertEquals(3, st.size());
    Assertions.assertNull(st.get("cat"));

    st.delete("cam");
    st.delete("cargo");
    st.delete("car");
  }

  @Test
  @DisplayName("test of contains operation")
  void test4() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    st.put("car", "kia");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");
    st.put("cat", "nope");

    Assertions.assertTrue(st.contains("car"));
    Assertions.assertEquals(false, st.contains("ca"));
    Assertions.assertEquals(false, st.contains("c"));
  }

  @Test
  @DisplayName(("test of keys operation"))
  void test5() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    st.put("cat", "nope");
    st.put("car", "kia");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");

    List<String> expected = Arrays.asList("cam", "car", "cargo", "cat");

    List<String> actual = new ArrayList<>(4);
    // method under test
    for (String key : st.keys()) {
      actual.add(key);
    }

    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  @DisplayName(("test of keysWithPrefix operation"))
  void test6() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
    st.put("cat", "nope");
    st.put("car", "kia");
    st.put("computer", "desktop");
    st.put("cargo", "pants");
    st.put("cam", "nikkon");
    st.put("dummy", "dummy-value");

    List<String> expected = Arrays.asList("car", "cargo");

    List<String> actual = new ArrayList<>(2);
    // method under test
    for (String key : st.keysWithPrefix("car")) {
      actual.add(key);
    }
    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  @DisplayName(("test of longestPrefixOf operation"))
  void test7() {
    SimpleTrieST<String> st = new SimpleTrieST<>();
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
