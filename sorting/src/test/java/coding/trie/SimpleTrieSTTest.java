package coding.trie;

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
  }
}
