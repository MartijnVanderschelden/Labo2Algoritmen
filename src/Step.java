public class Step {
    int time;
    Coordinate coordinate;

    public Step(int time, Coordinate coordinate){
        this.time = time;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getTime() {
        return time;
    }
}
