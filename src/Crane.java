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

    public Crane(int id, int x, int y, int xmin, int xmax, int ymin, int ymax, int xspeed, int yspeed){
        this.id = id;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.currentPos = new Coordinate(x, y);
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
                '}';
    }
}
