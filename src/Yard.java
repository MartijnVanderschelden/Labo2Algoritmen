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
    ArrayList<ArrayList<Container>> containerStack;
    boolean[][][] grid;
    int height;

    public Yard() {
        this.height = 0;
        this.name = null;
        this.cranes = new ArrayList<>();
        this.targetPosition = new HashMap<>();
        this.containerStack = new ArrayList<>();
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

    /* methode werkt niet meer, getHeight() methode was verkeerd
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
     */

    public void generateFeasibleInitialStack(){
        /*
        STAP 1
        Genereer een lijst waarin de containers gesorteerd zitten
        op lengte. Plaats ze daarna op een stack.
        Grootste onderaan, kleinste bovenaan
         */
        ArrayList<Container> sortedContainerList = new ArrayList<>();
        for (Container c :
                containers) {
            sortedContainerList.add(c);
        }
        Collections.sort(sortedContainerList, new sortByLength());
        Collections.reverse(sortedContainerList);

        Stack<Container> stack = new Stack<>();
        for (Container c :
                sortedContainerList) {
            stack.push(c);
        }
        /*
        STAP 2
        Plaats de containers volgens de assignments in de grid.

        Strategie:
        De kleinste containers eerst, daarna de grotere.
        Als het slot al ingenomen is, wordt de container
        toegevoegd aan een lijst die in aanmerking komt
        voor de volgende verdieping.
         */

        ArrayList<Container> temp = new ArrayList<>();
        while (!stack.empty()){
            Container container = stack.peek();

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
