import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Yard {

    // Input
    String name;
    int length;
    int width;
    int maxHeight;
    Slot[] slots;
    Container[] containers;
    ArrayList<Crane> cranes;


    // Target
    int targetHeight;

    // Key: containerID, Value: slotID
    HashMap<Integer, Integer> targetPosition;

    public Yard() {
        this.name = null;
        this.cranes = new ArrayList<>();
        this.targetPosition = new HashMap<>();
    }

    public void createArrays(int numberOfSlots, int numberOfContainers){
        this.slots = new Slot[numberOfSlots];
        this.containers = new Container[numberOfContainers];
    }

    public void addSlot(Slot slot){
        slots[slot.id] = slot;
    }

    public void addContainer(Container container){
        containers[container.id] = container;
    }

    public void addCrane(Crane crane){
        cranes.add(crane);
    }

    public void addTargetPosition(int containerId, int slotId){
        targetPosition.put(containerId, slotId);
    }
    // Constraints
    public boolean checkConstraints(ArrayList<Slot> slots){
        boolean allowed = true;
        for(int i=0; i<slots.size(); i++){
            if(slots.get(i).getHeight()+1 > maxHeight){
                allowed = false;
            }
        }

        int equalHeight = slots.get(0).getHeight();
        for(int i=1; i<slots.size()-1; i++){
            if(slots.get(i).getHeight() != equalHeight){
                allowed = false;
            }
        }
        return allowed;
    }

    @Override
    public String toString() {
        return "Yard{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", maxHeight=" + maxHeight +
                ", slots=" + Arrays.toString(slots) +
                ", containers=" + Arrays.toString(containers) +
                ", cranes=" + cranes +
                ", targetMaxHeight=" + targetHeight +
                ", targetPosition=" + targetPosition +
                '}';
    }
}
