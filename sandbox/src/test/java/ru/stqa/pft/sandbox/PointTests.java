package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance() {
    Point p1 = new Point(1, 0);
    Point p2 = new Point(1, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
    Assert.assertTrue(p1.distance(p2) >= 0.0);
  }
}
