package main;

import java.util.ArrayList;

public class Yard {
    ArrayList<Slot> slots;
    ArrayList<Container> containers;
    ArrayList<Crane> cranes;

    public Yard() {
        this.slots = new ArrayList<>();
        this.containers = new ArrayList<>();
        this.cranes = new ArrayList<>();
    }

    public void addSlot(Slot slot){
        slots.add(slot);
    }

    public void addContainer(Container container){
        containers.add(container);
    }

    public void addCrane(Crane crane){
        cranes.add(crane);
    }

}
