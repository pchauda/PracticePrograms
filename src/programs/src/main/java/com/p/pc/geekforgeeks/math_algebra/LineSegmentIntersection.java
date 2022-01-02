package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given two line segments, find out if they intersect or not.</p>
 *
 * Approach: <br/>
 *  Two segments (p1,q1) and (p2,q2) intersect if and only if one of the following two conditions is verified
 *
 * 1. General Case:
 * – (p1, q1, p2) and (p1, q1, q2) have different orientations and
 * – (p2, q2, p1) and (p2, q2, q1) have different orientations.
 *
 * 2. Special Case
 * – (p1, q1, p2), (p1, q1, q2), (p2, q2, p1), and (p2, q2, q1) are all collinear and
 * – the x-projections of (p1, q1) and (p2, q2) intersect
 * – the y-projections of (p1, q1) and (p2, q2) intersect
 */
public class LineSegmentIntersection {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point q1 = new Point(5, 5);
        System.out.println(checkIntersection(p1, q1, new Point(5, 1), new Point(1, 5))); // true
        System.out.println(checkIntersection(p1, q1, new Point(1, 5), new Point(5, 1))); // true
        System.out.println(checkIntersection(p1, q1, new Point(-1, -1), new Point(0, 0))); // false
    }

    private static boolean checkIntersection(Point p1, Point q1, Point p2, Point q2) {
        Orientation o1 = findOrientation(p1, q1, p2);
        Orientation o2 = findOrientation(p1, q1, q2);
        Orientation o3 = findOrientation(p2, q2, p1);
        Orientation o4 = findOrientation(p2, q2, q1);
        // general case
        if(o1 != o2 && o3 != o4) return true;
        // collinear case
        if(o1 == Orientation.COLLINEAR && onSegmentForCollinearPoints(p1, q1, p2)) return true;
        if(o2 == Orientation.COLLINEAR && onSegmentForCollinearPoints(p1, q1, q2)) return true;
        if(o3 == Orientation.COLLINEAR && onSegmentForCollinearPoints(p2, q2, p1)) return true;
        if(o4 == Orientation.COLLINEAR && onSegmentForCollinearPoints(p2, q2, q1)) return true;
        return false;
    }

    // Find orientation using slope, if slope is increasing then counter-clockwise, decreasing then clockwise else collinear.
    private static Orientation findOrientation(Point p, Point q, Point r) {
        int slopeDelta = (r.y - q.y) * (q.x - p.x) - (q.y - p.y) * (r.x - q.x);
        if(slopeDelta == 0) return Orientation.COLLINEAR;
        return slopeDelta > 0 ? Orientation.COUNTERCLOCKWISE : Orientation.CLOCKWISE;
    }

    // check if collinear point is on segment, both x and y projection should be in range
    private static boolean onSegmentForCollinearPoints(Point p, Point q, Point r) {
        return r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x) &&
                r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y);
    }

    enum Orientation {
        CLOCKWISE, COUNTERCLOCKWISE, COLLINEAR
    }

    public static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
