package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given a line equation ax + by = c and two points (x1, y1) and (x2, y2), determine if both points lie on the
 * same side or opposite side of the line.</p>
 *
 * Approach: <br/>
 *  calculate the (ax + by - c) for both points if the sign is the same then both lie on the same side else not.
 */
public class PointsPositionRelativeToLine {

    public static void main(String[] args) {
        int a = 5, b = 2, c = 15;

        System.out.println(findRelativePosition(a, b, c, new Point(1, 1), new Point(10, 3))); // OPPOSITE_SIDE
        System.out.println(findRelativePosition(a, b, c, new Point(1, 1), new Point(2, 2))); // SAME_SIDE
    }

    private static Position findRelativePosition(int a, int b, int c, Point p, Point p1) {
        int positionP = (a * p.x + b * p.y - c);
        int positionP1 = (a * p1.x + b * p1.y - c);

        return positionP * positionP1 > 0 ? Position.SAME_SIDE : Position.OPPOSITE_SIDE;
    }

    enum Position {
        SAME_SIDE, OPPOSITE_SIDE
    }

    static class Point {
        int x,y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
