package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given two circles, find out if circles intersect each other or not.
 * Similarly also find out if smaller circle is completely inside the larger circle or not.</p>
 * <p>
 * Approach:<br/>
 * Calculate the distance between the center of both circle, and then compare this distance with radius of both circles
 * using below cases:
 * 1. distance of centers = r1 + r2 => Circles touch each other
 * 2. distance of centers > r1 + r2 => Circles do not intersect
 * 3. distance of centers < r1 + r2 => Circles intersect
 * <p>
 * similarly, if you want to check if smaller circle is inside the larger one then use below cases:
 * 1. distance of centers + rs < rl => Smaller circle is completely inside the larger circle
 * 2. distance of centers + rs = rl => Smaller circle is completely inside the larger circle and touches outer circle
 * 1. distance of centers + rs > rl => Smaller circle is not completely inside the larger circle
 */
public class CirclesIntersection {
    public static void main(String[] args) {
        // circles are represented by its center and radius
        System.out.println(
                checkCircleState(new Circle(new Point(4, 4), 2), new Circle(new Point(0, 4), 1))); // Outside
        System.out.println(
                checkCircleState(new Circle(new Point(4, 4), 2), new Circle(new Point(0, 4), 2))); // TouchOutside
        System.out.println(
                checkCircleState(new Circle(new Point(4, 4), 2), new Circle(new Point(0, 4), 3))); // Intersect
        System.out.println(
                checkCircleState(new Circle(new Point(4, 4), 2), new Circle(new Point(3, 4), 1))); // TouchInside
        System.out.println(
                checkCircleState(new Circle(new Point(4, 4), 3), new Circle(new Point(3, 4), 1))); // Inside
    }

    private static CircleState checkCircleState(Circle c1, Circle c2) {
        // identify smaller and larger circle
        Circle cs = c1.radius < c2.radius ? c1 : c2;
        Circle cl = c1.radius < c2.radius ? c2 : c1;

        int centerDistance = (int) Math.sqrt(Math.pow(cl.center.x - cs.center.x, 2) + Math.pow(cl.center.y - cs.center.y, 2));
        if (centerDistance + cs.radius < cl.radius) return CircleState.Inside;
        else if (centerDistance + cs.radius == cl.radius) return CircleState.TouchInside;
        else if (centerDistance < cs.radius + cl.radius) return CircleState.Intersect;
        else if (centerDistance == cs.radius + cl.radius) return CircleState.TouchOutside;
        else return CircleState.Outside;
    }

    enum CircleState {
        Inside, Outside, Intersect, TouchInside, TouchOutside
    }

    static class Circle {
        Point center;
        int radius;

        Circle(Point center, int radius) {
            this.center = center;
            this.radius = radius;
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
