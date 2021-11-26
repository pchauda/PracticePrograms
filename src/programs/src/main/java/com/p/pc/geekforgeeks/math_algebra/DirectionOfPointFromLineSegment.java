package com.p.pc.geekforgeeks.math_algebra;

/**
 * Given a directional line segment (P -> Q) and a point (R), find out the direction of the point with respect to the
 * line segment i.e. if the point lies on the LEFT or RIGHT or COLLINEAR.
 * This technique is used in maps to identify the left vs right turns.
 * Approach:
 *  Calculate the cross product of the point with line segment with respect to origin. If the cross product is positive
 *  then point lies on the RIGHT, if negative then on LEFT else COLLINEAR
 */
public class DirectionOfPointFromLineSegment {
    public static void main(String[] args) {
        Point[] lineSegment = new Point[] {new Point(4, 6), new Point(1, 11)};

        System.out.println(checkDirection(lineSegment, new Point(4, 11))); // RIGHT
        System.out.println(checkDirection(lineSegment, new Point(1, 2))); // LEFT
        System.out.println(checkDirection(lineSegment, new Point(7, 2))); // RIGHT
    }

    private static Direction checkDirection(Point[] lineSegment, Point point) {
        Point start = lineSegment[0], end = lineSegment[1];
        // Calculate the cross product for directional line segment y adjusting the end to make it origin
        int crossProduct = (start.x - end.x) * (point.y - end.y) - (start.y - end.y) * (point.x - end.x);
        if(crossProduct == 0) return Direction.COLLINEAR;
        return crossProduct > 0 ? Direction.RIGHT : Direction.LEFT;
    }

    // inner-enums are always static
    enum Direction { LEFT, RIGHT, COLLINEAR};

    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
