package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given a line and a circle, find out if the line touches the circle, intersects or outside the circle.</p>
 *
 * Approach: <br/>
 *  Calculate the perpendicular distance (p) of the line from the center of the circle. If p > radius then outside,
 *  p = radius then touches, else intersects.
 */
public class LineIntersectionWithCircle {

    public static void main(String[] args) {
        // Circle is represented with center and radius. Line is represented with a, b, c parameters using ax + by + c = 0 equation.
        Point center = new Point(0, 0); int radius = 3;
        System.out.println(checkLineLocation(center, radius, 1, 2, 3)); // Intersect
        System.out.println(checkLineLocation(center, radius, 1, 1, 8)); // Outside
        System.out.println(checkLineLocation(center, radius, 3, 4, 15)); // Touches
    }

    private static LineLocation checkLineLocation(Point center, int radius, int a, int b, int c) {
        double perpendicularDistance = Math.abs(a * center.x + b * center.y + c) / Math.sqrt(a * a + b * b);
        if(perpendicularDistance == radius) return LineLocation.Touches;
        return perpendicularDistance > radius ? LineLocation.Outside : LineLocation.Intersect;
    }

    enum LineLocation {Touches, Outside, Intersect}

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
