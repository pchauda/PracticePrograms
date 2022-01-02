package com.p.pc.geekforgeeks.math_algebra;

/**
 * <p>Given a point and polygon, find out if the point lies inside or outside.</p>
 *
 * Approach: <br/>
 *  If a point is inside the polygon, then an infinite line drawn from the point in the right direction should cross odd
 *  number of edges else the point is outside.
 *  For points on the polygon itself, check for point in line segment condition.
 */
public class PointInsidePolygon {

    public static void main(String[] args) {
        Point[] polygon = new Point[] {new Point(0, 0), new Point(4, 1),
                new Point(3, 5), new Point(2, 4), new Point(1, 3)};
        System.out.println(isPointInsidePolygon(polygon, new Point(2, 2)));
        System.out.println(isPointInsidePolygon(polygon, new Point(5, 5)));
    }

    private static boolean isPointInsidePolygon(Point[] polygon, Point point) {
        // Polygon should at least have 3 points
        if(polygon.length < 3) return false;

        // Using infinity will cause overflow issues while computing slope hence using a very high number but not infinity for extreme
        Point extreme = new Point(10000000, point.y);
        int count = 0;
        for(int i=0; i < polygon.length; i++) {
            Point curr = polygon[i]; Point next = polygon[(i+1) % polygon.length];
            if(checkIntersection(curr, next, point, extreme)) {
                // check for collinear edge case
                if(findOrientation(curr, next, point) == Orientation.Collinear) {
                    return checkOnLineSegment(curr, next, point);
                }
                count++;
            }
        }
        return count % 2 == 1;
    }

    // Check orientation, if orientation is different then line segment intersect, else check for collinear and on-line-segment
    private static boolean checkIntersection(Point p1, Point q1, Point p2, Point q2) {
        Orientation o1 = findOrientation(p1, q1, p2);
        Orientation o2 = findOrientation(p1, q1, q2);
        Orientation o3 = findOrientation(p2, q2, p1);
        Orientation o4 = findOrientation(p2, q2, q1);

        if(o1 != o2 && o3 != o4) return true;
        if(o1 == Orientation.Collinear && checkOnLineSegment(p1, q1, p2)) return true;
        if(o2 == Orientation.Collinear && checkOnLineSegment(p1, q1, q2)) return true;
        if(o3 == Orientation.Collinear && checkOnLineSegment(p2, q2, p1)) return true;
        if(o4 == Orientation.Collinear && checkOnLineSegment(p2, q2, q1)) return true;
        return false;
    }

    private static Orientation findOrientation(Point p, Point q, Point r) {
        int slopeDelta = (r.y - q.y) * (q.x - p.x) - (q.y - p.y) * (r.x - q.x);

        if(slopeDelta == 0) return Orientation.Collinear;
        return slopeDelta > 0 ? Orientation.CounterClockwise : Orientation.Clockwise;
    }

    private static boolean checkOnLineSegment(Point p, Point q, Point r) {
        return r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x) &&
                r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y);
    }

    enum Orientation {Clockwise, CounterClockwise, Collinear}

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
