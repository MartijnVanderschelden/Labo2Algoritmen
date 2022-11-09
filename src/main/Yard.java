package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Yard {
    String name;
    HashMap<Integer, Slot> slots;
    HashMap<Integer, Container> containers;
    ArrayList<Crane> cranes;

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

}
