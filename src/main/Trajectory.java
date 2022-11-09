package main;

import java.util.*;

public class Trajectory {
    private List<Step> traject = new ArrayList<>();
    final int fieldX;
    final int fieldY;

    public Trajectory(int fieldX, int fieldY){
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public void addStep(int time, Coordinate coordinate){
        traject.add(new Step(time, coordinate));
    }

    public List<Step> getTraject(){
        return traject;
    }

    @Override
    public String toString() {
        return "main.Trajectory{" +
                "traject=" + traject +
                ", fieldX=" + fieldX +
                ", fieldY=" + fieldY +
                '}';
    }


}
