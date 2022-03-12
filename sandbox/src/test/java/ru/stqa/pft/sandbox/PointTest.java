package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance(){
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2,4);
        Assert.assertEquals(p1.distance(p2), 2.23606797749979);
    }
}
