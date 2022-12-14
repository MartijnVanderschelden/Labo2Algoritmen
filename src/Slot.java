import java.util.ArrayList;

public class Slot {
    int id;
    Coordinate coordinate;

    ArrayList<Container> containerStack;

    int minAllowedLength;

    public Slot(int id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
        this.containerStack = new ArrayList<>();
    }

    public int getHeight(){
        return containerStack.size();
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", containerStack=" + containerStack +
                ", minAllowedLength=" + minAllowedLength +
                '}';
    }
}
