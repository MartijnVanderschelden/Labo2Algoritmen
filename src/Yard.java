import java.util.*;

public class Yard {
    // Input
    String name;
    int length;
    int width;
    int maxHeight;
    int targetHeight;
    Slot[] slots;
    Container[] containers;
    ArrayList<Crane> cranes;
    HashMap<Integer, Integer> targetPosition; // Key: containerID, Value: slotID
    //used datastructeres and additional variables
    boolean [][][] grid;
    ArrayList<ArrayList<Container>> containerSurfacePerLvl;

    public Yard() {
        this.name = null;
        this.cranes = new ArrayList<>();
        this.targetPosition = new HashMap<>();
        this.containerSurfacePerLvl = new ArrayList<>();
    }
    public void createArrays(int numberOfSlots, int numberOfContainers){
        this.slots = new Slot[numberOfSlots];
        this.containers = new Container[numberOfContainers];
        this.grid = new boolean[length][width][maxHeight];
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
    public void generateFeasibleInitialStack(){
        Stack<Container> stack = new Stack<>();
        for (int i = 0; i < containers.length; i++) {
            stack.push(containers[containers.length-1-i]);
        }
        boolean[][][] yardIn3D = new boolean[length][width][maxHeight];
        int level = 0;
        ArrayList<Container> temp = new ArrayList<>();
        while (!stack.empty()){
            Container currContainer = stack.peek();
            Coordinate c = currContainer.getCoordinate(length);
            System.out.println(level + " " + currContainer.id);
            if (yardIn3D[(int) c.x][(int) c.y][level]){
                containerSurfacePerLvl.add(temp);
                temp = new ArrayList<>();
                level++;
            } else {
                for (int i = 0; i < currContainer.length; i++) {
                    yardIn3D[(int) c.x + currContainer.length -1][(int) c.y][level] = true;
                }
                temp.add(currContainer);
                stack.pop();
            }
        }
        containerSurfacePerLvl.add(temp);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < maxHeight; k++) {
                    grid[i][j][k] = yardIn3D[i][j][k];
                }
            }
        }
        System.out.println(containerSurfacePerLvl);
        for (ArrayList<Container> cList :
                containerSurfacePerLvl) {
            System.out.println(cList.size());
        }
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
