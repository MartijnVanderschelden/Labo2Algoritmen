package main;

public class Container {
    int id;
    int length;

    Slot[] slots;

    int height;

    public Container(int id, int length) {
        this.id = id;
        this.length = length;
        this.slots = new Slot[length];
        this.height = 0;
    }

    public void changePosition(Slot[] slots, int height){
        this.slots = slots;
        this.height = height;
    }

}
