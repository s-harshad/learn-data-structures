package coding.uf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class WeightedQuickUnionPathCompressionTest {

  @Test
  @DisplayName(
      "WeightedQuickUnionPathCompression number of components should equal number of elements, INITIALLY")
  void count_1() {
    UF uf = new WeightedQuickUnionPathCompression(10);
    Assertions.assertEquals(10, uf.count());
  }

  @Test
  @DisplayName(
      "WeightedQuickUnionPathCompression one call to union, should decrease connected components by 1")
  void count_2() {
    UF uf = new WeightedQuickUnionPathCompression(10);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
  }

  @Test
  @DisplayName(
      "WeightedQuickUnionPathCompression calling union with the same parameters, should only decrease the count multiple times")
  void count_3() {
    UF uf = new WeightedQuickUnionPathCompression(10);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
    uf.union(4, 3);
    uf.union(4, 3);
    uf.union(4, 3);
    uf.union(4, 3);
    Assertions.assertEquals(9, uf.count());
  }

  @Test
  @DisplayName("WeightedQuickUnionPathCompression test 2 distinct connected components")
  void count_4() {
    UF uf = new WeightedQuickUnionPathCompression(10);
    uf.union(4, 3);
    uf.union(3, 8);
    uf.union(9, 4);
    uf.union(8, 9);

    uf.union(6, 5);
    uf.union(2, 1);
    uf.union(5, 0);
    uf.union(7, 2);
    uf.union(6, 1);
    uf.union(1, 0);
    uf.union(6, 7);
    Assertions.assertEquals(2, uf.count());
  }

  @Test
  @DisplayName("WeightedQuickUnionPathCompression test initially not components are connected")
  void connected_1() {
    UF uf = new WeightedQuickUnionPathCompression(10);
    boolean actual = uf.isConnected(4, 3);
    Assertions.assertEquals(false, actual);
  }

  @Test
  @DisplayName("WeightedQuickUnionPathCompression test for Exception when index is out of bounds")
  void testException() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          UF uf = new QuickUnion(10);
          uf.find(10);
        });
  }
}
