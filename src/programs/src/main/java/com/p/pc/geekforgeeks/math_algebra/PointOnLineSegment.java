package com.p.pc.geekforgeeks.math_algebra;

/**
 * Given a point and a line segment represented by two points, find out if the point lies on the line segment.
 *
 * Approach:
 *  First check if all three points are collinear, if no then point is not on the line. If yes, then check with the
 *  point is within the line segment.
 */
public class PointOnLineSegment {

    public static void main(String[] args) {
        Point[] lineSegment = new Point[] {new Point(1, 1), new Point(5, 5)};

        System.out.println(isPointOnLineSegment(lineSegment, new Point(2, 2))); // True
        System.out.println(isPointOnLineSegment(lineSegment, new Point(2, 1))); // False
        System.out.println(isPointOnLineSegment(lineSegment, new Point(6, 6))); // False
        System.out.println(isPointOnLineSegment(lineSegment, new Point(0, 1))); // False
    }

    private static boolean isPointOnLineSegment(Point[] lineSegment, Point r) {
        // if point lies within both x and y projection then point lies in line segment
        Point p = lineSegment[0]; Point q = lineSegment[1];
        // Adjust the coordinates of point p from q and r to make p as origin (0,0)
        // using cross product to check if all points are collinear
        int crossProduct = (q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x);
        if(crossProduct != 0) return false;
        // check if r is within line segment, using x and y projection. If both x and y projection are within the line
        // segment then point lies on the segment else not
        return (r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x)) &&
                (r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y));
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
