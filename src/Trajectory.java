import java.util.*;

public class Trajectory {
    private Map<Integer, Coordinate> traject = new HashMap<>();
    final int fieldX;
    final int fieldY;

    public Trajectory(int fieldX, int fieldY){
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public void addTraject(int time, Coordinate coordinate){
        traject.put(time, coordinate);
    }

    public Map<Integer, Coordinate> getTraject(){
        return traject;
    }
}
