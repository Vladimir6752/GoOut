package dev.vladimir.models.baseComponents.logic;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Point point) {
        x += point.x;
        y += point.y;
    }

    public Point multiply(int i) {
        x *= i;
        y *= i;
        return this;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double distanceToPoint(Point point) {
        double first = point.x - x;
        double second = point.y - y;

        return Math.sqrt(first * first + second * second);
    }
}
