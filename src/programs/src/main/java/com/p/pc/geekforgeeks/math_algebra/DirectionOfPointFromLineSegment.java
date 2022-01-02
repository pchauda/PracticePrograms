package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given a directional line segment (P -> Q) and a point (R), find out the direction of the point with respect to the
 * line segment i.e. if the point lies on the LEFT or RIGHT or COLLINEAR.
 * This technique is used in maps to identify the left vs right turns.</p>
 * Approach: <br/>
 *  Calculate the slope of the given directional line segment (from start to end point), then calculate the slope between
 *  end point vs the given point. If slope is increasing then point is on the left, if decreasing then on the right, if
 *  no change in the slope then collinear.
 *
 *  Initial Slope (Is) = (y2 - y1) / (x2 - x1)
 *  Slope with given point (Sg) = (y - y2) / (x - x2)
 *
 *  If Sg > Is => Sg - Is > 0 then LEFT
 *  If Sg < Is => Is - Sg > 0 then RIGHT
 *  else COLLINEAR
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
        int crossProduct = (end.y - start.y) * (point.x - end.x) - (point.y - end.y) * (end.x - start.x) ;
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
