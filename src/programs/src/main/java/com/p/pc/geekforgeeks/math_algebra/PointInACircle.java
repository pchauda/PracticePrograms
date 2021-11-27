package com.p.pc.geekforgeeks.math_algebra;

/**
 * Given a circle and a point, find out if the point lies inside the circle or outside.
 *
 * Approach:
 *  Calculate distance of the given point from the center of the circle and compare it with radius. If distance less
 *  than radius then point is inside, if more, then outside else point is on the circle.
 */
public class PointInACircle {
    public static void main(String[] args) {
        // circle is represented by its center and radius.
        Point center = new Point(4, 4);
        int radius = 3;

        System.out.println(getPointLocation(center, radius, new Point(1, 1))); // Outside
        System.out.println(getPointLocation(center, radius, new Point(1, 4))); // OnCircle
        System.out.println(getPointLocation(center, radius, new Point(2, 2))); // Inside
    }

    private static PointLocation getPointLocation(Point center, int radius, Point point) {
        int sqrDistance = (point.x - center.x) * (point.x - center.x) + (point.y - center.y) * (point.y - center.y);
        if(sqrDistance == radius * radius) return PointLocation.OnCircle;
        return sqrDistance > radius * radius ? PointLocation.Outside : PointLocation.Inside;
    }

    enum PointLocation {
        Inside, Outside, OnCircle
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
