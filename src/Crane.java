public class Crane {
    private Trajectory traject;
    private int speed;
    private int safetyDistance;

    public Crane(int speed, int safetyDistance, int fieldX, int fieldY, Coordinate begin){
        this.traject = new Trajectory(fieldX, fieldY);
        traject.addTraject(0, begin);
        this.speed = speed;
        this.safetyDistance = safetyDistance;
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

    public void move(Coordinate begin, Coordinate eind){

    }
}
