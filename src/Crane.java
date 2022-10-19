import java.util.*;

import static java.lang.Math.max;

public class Crane {
    private Trajectory traject;
    private int speed;
    private int safetyDistance;
    private Coordinate currentPos;

    public Crane(int speed, int safetyDistance, int fieldX, int fieldY, Coordinate begin){
        this.traject = new Trajectory(fieldX, fieldY);
        traject.addStep(0, begin);
        this.speed = speed;
        this.safetyDistance = safetyDistance;
        this.currentPos =  begin;
    }

    public Trajectory getTraject() {
        return traject;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSafetyDistance() {
        return safetyDistance;
    }

    public void move(Coordinate eind, List<Crane> kranen){
        if(check(eind, kranen)){
            int timeX = Math.abs(eind.getX() - currentPos.getX()) * speed;
            int timeY = Math.abs(eind.getY() - currentPos.getY()) * speed;
            int time = max(timeX, timeY) + traject.getTraject().get(traject.getTraject().size()-1).getTime();

            traject.addStep(time, eind);
        }
    }

    public boolean check(Coordinate eind, List<Crane> kranen){
        boolean check = false;
        for(int i=0; i < kranen.size(); i++){
            if(Math.abs(kranen.get(i).currentPos.getX() - eind.getX()) > safetyDistance
            || Math.abs(kranen.get(i).currentPos.getY() - eind.getY()) > safetyDistance){
                check = true;
            }
        }
        return check;
    }

    @Override
    public String toString() {
        return "Crane{" +
                "traject=" + traject +
                ", speed=" + speed +
                ", safetyDistance=" + safetyDistance +
                '}';
    }
}
