public class Crane {
    private Trajectory traject;
    private int speed;
    private int safetyDistance;

    public Crane(Trajectory traject, int speed, int safetyDistance){
        this.traject = traject;
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
}
