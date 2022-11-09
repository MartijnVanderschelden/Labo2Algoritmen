import java.util.ArrayList;

public class Yard {
    ArrayList<Slot> slots;
    ArrayList<Container> containers;
    ArrayList<Crane> cranes;

    public Yard(ArrayList<Slot> slots, ArrayList<Container> containers, ArrayList<Crane> cranes) {
        this.slots = new ArrayList<>();
        this.containers = new ArrayList<>();
        this.cranes = new ArrayList<>();
    }
}
