public class Container {
    int id;
    int length;
    int slotId;
    int height;

    public Container(int id, int length) {
        this.id = id;
        this.length = length;
    }

    public Coordinate getCoordinate(int length){
        double x = slotId%length;
        double y = Math.floorDiv(slotId,length);
        return new Coordinate(x,y);
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
