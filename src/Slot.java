import java.util.ArrayList;

public class Slot {
    int id;
    Coordinate coordinate;

    ArrayList<Container> containers;

    int minAllowedLength;

    public Slot(int id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
        this.containers = new ArrayList<>();
    }

    //verkeerd


    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", containers=" + containers +
                ", minAllowedLength=" + minAllowedLength +
                '}';
    }
}
