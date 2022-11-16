package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Yard {
    String name;
    HashMap<Integer, Slot> slots;
    HashMap<Integer, Container> containers;
    ArrayList<Crane> cranes;

    int maxHeight;

    public Yard() {
        this.name = null;
        this.slots = new HashMap<>();
        this.containers = new HashMap<>();
        this.cranes = new ArrayList<>();
    }

    public void addSlot(Slot slot){
        slots.put(slot.id, slot);
    }

    public void addContainer(Container container){
        containers.put(container.id, container);
    }

    public void addCrane(Crane crane){
        cranes.add(crane);
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

}
