package main;

public class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "main.Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
