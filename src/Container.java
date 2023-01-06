public class Container {
    int id;
    int length;
    int slotId;
    int height;

    public Container(int id, int length) {
        this.id = id;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", length=" + length +
                ", slotId=" + slotId +
                '}';
    }
}
