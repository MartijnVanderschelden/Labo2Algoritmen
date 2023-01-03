import java.util.*;

public class Yard {
    // Input
    String name;
    int length;
    int width;
    int maxHeight;
    int targetHeight;
    Slot[] slots;

    int[][] mapOfSlots;

    Container[] containers;
    ArrayList<Crane> cranes;

    HashMap<Integer, Stack<Container>> currentPosition; // Key: containerID, Value: slotID
    HashMap<Integer, Stack<Container>> targetPosition;

    HashMap<Integer, Integer> targetPositionMapContainerKey;


//    ArrayList<Integer[]> containersToMove;// Key: containerID, Value: slotID
    ArrayList<Integer> containersToMove;// Key: containerID, Value: slotID

    //used datastructeres and additional variables
    boolean [][][] grid;
    ArrayList<ArrayList<Container>> containerSurfacePerLvl;


    public Yard() {
        this.name = null;
        this.cranes = new ArrayList<>();
        this.currentPosition = new HashMap<>();
        this.targetPosition = new HashMap<>();
        this.containerSurfacePerLvl = new ArrayList<>();
        this.containersToMove = new ArrayList<>();
        this.targetPositionMapContainerKey = new HashMap<>();
    }
    public void createArrays(int numberOfSlots, int numberOfContainers){
        this.slots = new Slot[numberOfSlots];
        this.containers = new Container[numberOfContainers];
        this.mapOfSlots = new int[length][width];
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

    public void getDifferences(){
        // Alle slots overlopen
        for(int i=0; i<slots.length; i++){
            // Alle containers op dat slot overlopen
            for(int j=0; j<slots[i].containers.size(); j++){
                if(!targetPosition.get(i).contains(currentPosition.get(i).get(j))){
                    if(!containersToMove.contains(currentPosition.get(i).get(j).id)){
                        containersToMove.add(currentPosition.get(i).get(j).id);
                    }
                }
            }
        }
    }

    public void getDestinationSlot(){
        for(int c : containersToMove){
            System.out.println("Cont: " + c + " Current pos: "+ containers[c].slotId + " Destination slotid: " + targetPositionMapContainerKey.get(c));
        }
    }

    public void moveContainers(){
        for(int cIdToMove : containersToMove){
            System.out.println("Slotid: " + targetPositionMapContainerKey.get(cIdToMove));
            int slotIdWithContainerToMove = containers[cIdToMove].slotId;

            ArrayList<Container> containersToMoveBack = new ArrayList<>();

            for(int i = currentPosition.get(slotIdWithContainerToMove).size()-1; i==0; i--){
                Container topCont = currentPosition.get(slotIdWithContainerToMove).get(i);
                int targetSlotId = targetPositionMapContainerKey.get(cIdToMove);
                if(topCont.id == cIdToMove){
                    if(ifPossibleToPlace(topCont.length, targetSlotId)){
                        currentPosition.get(topCont.slotId).remove(topCont);
                        System.out.println("Cont: " + topCont.id + " moved from: " + topCont.slotId + " to: " + targetSlotId);;
                        currentPosition.get(targetSlotId).add(topCont);
                        topCont.slotId = targetSlotId;
                        for(int j=containersToMoveBack.size()-1; j==0; j--){
                            Container cont = containersToMoveBack.get(j);
                            currentPosition.get(cont.slotId).remove(cont);
                            System.out.println("Cont: " + cont.id + " moved from: " + cont.slotId + " to: " + targetSlotId);
                            currentPosition.get(targetSlotId).add(cont);
                            cont.slotId = targetSlotId;
                            containersToMoveBack.remove(cont);
                        }
                    }
                } else{
                    int freeSlot = findFreePlace(topCont.length, topCont.slotId);
                    if(freeSlot == -1){
                        System.out.println("LOL PROBLEEM");
                    } else {
                        currentPosition.get(topCont.slotId).remove(topCont);
                        System.out.println("Cont: " + topCont.id + " moved from: " + topCont.slotId + " to: " + freeSlot);
                        currentPosition.get(freeSlot).add(topCont);
                        topCont.slotId = freeSlot;
                        containersToMoveBack.add(topCont);
                    }
                }
            }
        }
    }

    public boolean ifPossibleToPlace(int lengthContainer, int slotId){
        if(currentPosition.get(slotId).size()<maxHeight){
            // Container van breedte 1
            if(length == 1){
                if(currentPosition.get(slotId).peek().length == 1){
                    return true;
                }
                // Container van breedte meer dan 1
            } else {
                boolean valid = true;
                if((int) slots[slotId].coordinate.x+lengthContainer < length){
                    for(int i = 1; i < lengthContainer; i++){
                        int slotNextTo = mapOfSlots[(int) slots[slotId].coordinate.x+i][(int) slots[slotId].coordinate.y];
                        if(currentPosition.get(slotNextTo).size() != currentPosition.get(slotId).size()){
                            valid = false;
                        }
                        // TODO klopt niet volledig met containers van 3 op 2 (denk ik?)
                        if(currentPosition.get(slotNextTo).peek().length > lengthContainer){
                            valid = false;
                        }
                    }
                    return valid;
                }
            }
        }
        return false;
    }

    public int findFreePlace(int lengthContainer, int targetSlotId){
        for(int s=0; s<slots.length; s++){
            if(s == targetSlotId){
                break;
            }
            if(currentPosition.get(s).size()<maxHeight){
                // Container van breedte 1
                if(lengthContainer == 1){
                    if(currentPosition.get(s).size() == 0 || currentPosition.get(s).peek().length == 1){
                        return s;
                    }
                // Container van breedte meer dan 1
                } else {
                    boolean valid = true;
                    if((int) slots[s].coordinate.x+lengthContainer < length){
                        for(int i = 1; i < length; i++){
                            int slotNextTo = mapOfSlots[(int) slots[s].coordinate.x+i][(int) slots[s].coordinate.y];
                            if(currentPosition.get(slotNextTo).size() != currentPosition.get(s).size()){
                                valid = false;
                            }
                            if(currentPosition.get(slotNextTo).size() !=0 && currentPosition.get(slotNextTo).peek().length > lengthContainer){
                                valid = false;
                            }
                        }
                        if(valid){
                            return s;
                        }
                    }
                }
            }
        }
        return -1;
    }




    // ! ANDERE BRANCh
//    public void addTargetPosition(int containerId, int slotId){
//        targetPosition.put(containerId, slotId);
//    }
//    public void generateFeasibleInitialStack(){
//        Stack<Container> stack = new Stack<>();
//        for (int i = 0; i < containers.length; i++) {
//            stack.push(containers[containers.length-1-i]);
//        }
//        boolean[][][] yardIn3D = new boolean[length][width][maxHeight];
//        int level = 0;
//        ArrayList<Container> temp = new ArrayList<>();
//        while (!stack.empty()){
//            Container currContainer = stack.peek();
//            Coordinate c = currContainer.getCoordinate(length);
//            System.out.println(level + " " + currContainer.id);
//            if (yardIn3D[(int) c.x][(int) c.y][level]){
//                containerSurfacePerLvl.add(temp);
//                temp = new ArrayList<>();
//                level++;
//            } else {
//                for (int i = 0; i < currContainer.length; i++) {
//                    yardIn3D[(int) c.x + currContainer.length -1][(int) c.y][level] = true;
//                }
//                temp.add(currContainer);
//                stack.pop();
//            }
//        }
//        containerSurfacePerLvl.add(temp);
//
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < width; j++) {
//                for (int k = 0; k < maxHeight; k++) {
//                    grid[i][j][k] = yardIn3D[i][j][k];
//                }
//            }
//        }
//        System.out.println(containerSurfacePerLvl);
//        for (ArrayList<Container> cList :
//                containerSurfacePerLvl) {
//            System.out.println(cList.size());
//        }
//    }

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
