package com.p.pc.geekforgeeks.math_algebra;

/**
 * Given 3 ordered points, find its orientation i.e. clockwise, counterclockwise, collinear.
 *
 * Approach:
 *  Find slope of first two points and then compare it last two points. If slope is increasing then orientation is
 *  counterclockwise, if decreasing then clockwise else collinear.
 *
 *  For given points (x1, y1), (x2, y2), (x3, y3)
 *
 *  Slope1 = (y2 - y1) / (x2 - x1)
 *  Slope2 = (y3 - y2) / (x3 - x2)
 *
 *  if Slope2 > Slope1 => Counterclockwise, this can be represented as
 *  (y3 - y2) / (x3 - x2) > (y2 - y1) / (x2 - x1)   or
 *  (y3 - y2) * (x2 - x1) > (y2 - y1) * (x3 - x2)   or
 *  we will use below formula to compute the orientation
 *  (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) > 0 => counterclockwise
 *  (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) < 0 => clockwise
 *  (y3 - y2) * (x2 - x1) - (y2 - y1) * (x3 - x2) = 0 => collinear
 */
public class OrientationProblem {
    public static void main(String[] args) {
        Point[] collinearPts = new Point[]{new Point(0, 0), new Point(4, 4), new Point(1, 1)};
        Point[] clockWisePts = new Point[]{new Point(0, 0), new Point(4, 4), new Point(4, 2)};
        Point[] counterClockWisePts = new Point[]{new Point(0, 0), new Point(4, 4), new Point(5, 6)};

        System.out.println(findOrientation(collinearPts)); // Collinear
        System.out.println(findOrientation(clockWisePts)); // ClockWise
        System.out.println(findOrientation(counterClockWisePts)); // CounterClockWise
    }

    static String findOrientation(Point[] points) {
        if(points.length != 3) throw new IllegalArgumentException("Incorrect arguments");
        Point p1 = points[0]; Point p2 = points[1]; Point p3 = points[2];
        int slopeDelta = (p3.y - p2.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p3.x - p2.x);
        if(slopeDelta == 0) return "Collinear";
        return slopeDelta > 0 ? "CounterClockWise" : "ClockWise";
    }

    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
