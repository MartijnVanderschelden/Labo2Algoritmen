package main;

import java.util.*;

import static java.lang.Math.max;

public class Crane {
    int id;
    int xspeed;
    int yspeed;
    int xmin;
    int xmax;
    int ymin;
    int ymax;

    Coordinate currentPos;
    Trajectory traject;

    public Crane(int id, double x, double y, int xmin, int xmax, int ymin, int ymax, int xspeed, int yspeed){
        this.id = id;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.currentPos = new Coordinate(x, y);
        //this.traject = new Trajectory(fieldX, fieldY);
        //traject.addStep(0, begin);
    }


    public void move(Coordinate eind, List<Crane> kranen){
        if(check(eind, kranen)){
            double timeX = Math.abs(eind.x - currentPos.x) * xspeed;
            double timeY = Math.abs(eind.y - currentPos.y) * yspeed;
            double time = max(timeX, timeY) + traject.getTraject().get(traject.getTraject().size()-1).getTime();

            //traject.addStep(time, eind);
        }
    }

    public boolean check(Coordinate eind, List<Crane> kranen){
        boolean check = false;
        for(int i=0; i < kranen.size(); i++){
            if(Math.abs(kranen.get(i).currentPos.x - eind.x) > 1
            || Math.abs(kranen.get(i).currentPos.y - eind.y) > 1){
                check = true;
            }
        }
        return check;
    }

    public void moveContainer(Container container, Slot slotBegin, Slot slotEnd){

    }

    @Override
    public String toString() {
        return "Crane{" +
                "id=" + id +
                ", xspeed=" + xspeed +
                ", yspeed=" + yspeed +
                ", xmin=" + xmin +
                ", xmax=" + xmax +
                ", ymin=" + ymin +
                ", ymax=" + ymax +
                ", currentPos=" + currentPos +
                ", traject=" + traject +
                '}';
    }
}
