package coding.uf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class QuickFindTest {

  @Test
  @DisplayName("QuickFind number of components should equal number of elements, INITIALLY")
  void count_1() {
    UF uf = new QuickFind(10);
    Assertions.assertEquals(10, uf.count());
  }

  @Test
  @DisplayName("QuickFind one call to union, should decrease connected components by 1")
  void count_2() {
    QuickFind uf = new QuickFind(10);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
  }

  @Test
  @DisplayName(
      "QuickFind calling union with the same parameters, should only decrease the count multiple times")
  void count_3() {
    UF uf = new QuickFind(10);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
    uf.union(4, 3);
    uf.union(4, 3);
    uf.union(4, 3);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
  }

  @Test
  @DisplayName("QuickFind test initially not components are connected")
  void connected_1() {
    UF uf = new QuickFind(10);
    boolean actual = uf.isConnected(4, 3);
    Assertions.assertEquals(false, actual);
  }

  @Test
  @DisplayName("QuickFind test for Exception when index is out of bounds")
  void testException() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          UF uf = new QuickFind(10);
          uf.find(10);
        });
  }
}
