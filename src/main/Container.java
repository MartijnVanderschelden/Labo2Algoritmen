package main;

public class Container {
    int id;
    int length;
    int slotId;
    int height;

    public Container(int id, int length) {
        this.id = id;
        this.length = length;
        this.height = 0;
    }

    public void changePosition(int slotId, int height){
        this.slotId = slotId;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", length=" + length +
                ", slotId=" + slotId +
                ", height=" + height +
                '}';
    }
}
