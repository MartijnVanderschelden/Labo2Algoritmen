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

    List<Integer> containersToMove;// Key: containerID, Value: slotID

    // Output
    ArrayList<Traject> traject;

    int time;

    public Yard() {
        this.cranes = new ArrayList<>();
        this.currentPosition = new HashMap<>();
        this.targetPosition = new HashMap<>();
        this.containersToMove = new ArrayList<>();
        this.targetPositionMapContainerKey = new HashMap<>();
        this.traject = new ArrayList<>();
        time=0;
    }

    public void createArrays(int numberOfSlots, int numberOfContainers){
        this.slots = new Slot[numberOfSlots];
        this.containers = new Container[numberOfContainers];
        this.mapOfSlots = new int[length][width];
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


//////////////////////////////////////////////////////////
// ALGO VOOR TARGET POSITION
// ////////////////////////////////////////////////////////
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
        while(!containersToMove.isEmpty()){
            Container containerToMove = containers[containersToMove.get(0)];
//            System.out.println("Slotid: " + targetPositionMapContainerKey.get(cIdToMove))
            int slotIdWithContainerToMove = containerToMove.slotId;

            ArrayList<Container> containersToMoveBack = new ArrayList<>();

            for(int i = currentPosition.get(slotIdWithContainerToMove).size()-1; i==0; i--){
                Container topCont = currentPosition.get(slotIdWithContainerToMove).get(i);
                int targetSlotId = targetPositionMapContainerKey.get(containerToMove.id);
                if(topCont.equals(containerToMove)){
                    if(ifPossibleToPlace(topCont.length, targetSlotId)){
                        calcCraneMovement(topCont, topCont.slotId, targetSlotId);
                        moveContainer(topCont, targetSlotId);
                        for(int j=containersToMoveBack.size()-1; j==0; j--){
                            Container cont = containersToMoveBack.get(j);
                            calcCraneMovement(cont, cont.slotId, targetSlotId);
                            moveContainer(cont, targetSlotId);
                            containersToMoveBack.remove(cont);
                        }
                    }
                } else{
                    int freeSlot = findFreePlace(topCont.length, topCont.slotId);
                    if(freeSlot == -1){
                        containersToMove.remove(0);
                        containersToMove.add(containerToMove.id);
                        System.out.println("PROBLEEM");
                        break;
                    } else {
                        calcCraneMovement(topCont, topCont.slotId, freeSlot);
                        moveContainer(topCont, freeSlot);
                        containersToMoveBack.add(topCont);
                    }
                }
            }
            // TODO fix dit. Klopt NIET
            containersToMove.remove(0);
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

//////////////////////////////////////////////////////////
// ALGO VOOR TARGET HEIGHT
// ////////////////////////////////////////////////////////
    public void getStacksTooHigh(){
        for(int i=0; i<slots.length; i++){
            Stack<Container> cloneContainerStack = (Stack<Container>) currentPosition.get(i).clone();
            while(cloneContainerStack.size() > targetHeight){
                if(!containersToMove.contains(cloneContainerStack.peek().id)){
                    containersToMove.add(cloneContainerStack.pop().id);
                } else {
                    cloneContainerStack.pop();
                }
            }
        }
    }

    public void lowerStacksToTargetHeight(){
        int sizeContainersToMove = containersToMove.size();
        int count = 0;
        while(!containersToMove.isEmpty()){
            int c = containersToMove.get(0);
            int freeSlot = findFreePlace(containers[c].length);
            if(freeSlot == -1){
                containersToMove.remove(0);
                containersToMove.add(c);
                if(sizeContainersToMove == containersToMove.size()){
                    count++;
                }
                if(count > 3){
                    makeStacksNextToEachOtherSameHeight();
                    break;
                }
            } else {
                calcCraneMovement(containers[c], containers[c].slotId, freeSlot);
                moveContainer(containers[c], freeSlot);
                containersToMove.remove(0);
            }
            sizeContainersToMove = containersToMove.size();
        }
    }

    // Slots zoeken met stacks waar er nog een container bij kan in de hoogte
    // Terwijl er rekening gehouden wordt met de constraints
    public int findFreePlace(int lengthContainer){
        for(int s=0; s<slots.length; s++){
            if(currentPosition.get(s).size()<targetHeight){
                // Container van breedte 1
                if(lengthContainer == 1){
                    if(currentPosition.get(s).size() == 0 || currentPosition.get(s).peek().length == 1){
                        return s;
                    }
                    // Container van breedte meer dan 1
                } else {
                    boolean valid = true;
                    // Nog genoeg afstand tot aan grens yard
                    if((int) slots[s].coordinate.x+lengthContainer < length){
                        int slotX = (int) slots[s].coordinate.x;
                        for(int i = 1; i < lengthContainer; i++){
                            int slotNextTo = mapOfSlots[slotX+i][(int) slots[s].coordinate.y];
                            // Controleren of stapels ernaast even hoog zijn
                            if(currentPosition.get(slotNextTo).size() != currentPosition.get(s).size()){
                                valid = false;
                            }
                            // TODO beter uitwerken verschillende groottes container op elkaar
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

    // Containers verzetten, zodat er voldoende slots naast elkaar dezelfde hoogte hebben
    public void makeStacksNextToEachOtherSameHeight() {
        // Zullen altijd containers zijn die langer zijn dan 1
        System.out.println("Plaats aan het vrijmaken");
        while(!containersToMove.isEmpty()) {
            int c = containersToMove.get(0);
            int lengthContainer = containers[c].length;

            for (int s = 0; s < slots.length; s++) {
                int onlyOneToMove = 0;
                int slotToFill = 0;
                boolean done = false;

                // Nog genoeg afstand tot aan grens yard
                if ((int) slots[s].coordinate.x + lengthContainer < length && currentPosition.get(s).size() < targetHeight-1){
                    int slotX = (int) slots[s].coordinate.x;

                    // Op splitsen op basis van lengte van de container
                    // Slots zoeken waar er slechts 1 container voor moet verzet worden
                    if(lengthContainer==2){
                        int slotNextTo = mapOfSlots[slotX + 1][(int) slots[s].coordinate.y];
                        if (currentPosition.get(slotNextTo).size()-1 == currentPosition.get(s).size()) {
                            slotToFill = s;
                            onlyOneToMove++;
                        } else if (currentPosition.get(slotNextTo).size() == currentPosition.get(s).size()-1) {
                            slotToFill = slotNextTo;
                            onlyOneToMove++;
                        }
                    } else if(lengthContainer==3){
                        int slotNextOne = mapOfSlots[slotX + 1][(int) slots[s].coordinate.y];
                        int slotNextTwo = mapOfSlots[slotX + 2][(int) slots[s].coordinate.y];
                        if (currentPosition.get(slotNextOne).size()-1 == currentPosition.get(s).size()
                                && currentPosition.get(slotNextOne).size() == currentPosition.get(slotNextTwo).size()) {
                            slotToFill = s;
                            onlyOneToMove++;
                        } else if (currentPosition.get(slotNextOne).size() == currentPosition.get(s).size()-1
                                && currentPosition.get(s).size() == currentPosition.get(slotNextTwo).size()) {
                            slotToFill = slotNextOne;
                            onlyOneToMove++;
                        } else if (currentPosition.get(slotNextTwo).size() == currentPosition.get(s).size()-1
                                && currentPosition.get(s).size() == currentPosition.get(slotNextOne).size()) {
                            slotToFill = slotNextTwo;
                            onlyOneToMove++;
                        }
                    }
                }

                if(onlyOneToMove == 1){
                    for(int i=0; i<currentPosition.size(); i++){
                        if(!currentPosition.get(i).isEmpty() && currentPosition.get(i).peek().length == 1 && i != slotToFill){
                            Container topCont = currentPosition.get(i).peek();
                            calcCraneMovement(topCont, topCont.slotId, slotToFill);
                            moveContainer(topCont, slotToFill);

                            // Container verzetten die nog te hoog staat
                            calcCraneMovement(containers[c], containers[c].slotId, s);
                            moveContainer(containers[c], s);

                            containersToMove.remove(containersToMove.indexOf(containers[c].id));

                            done = true;
                            break;
                        }
                    }
                }
                if(done){
                    break;
                }
            }
        }
    }

//////////////////////////////////////////////////////////
// ALGEMENE FUNCTIES: Container en kraan bewegingen
// ////////////////////////////////////////////////////////
    public void moveContainer(Container contToMove, int destSlot){
        System.out.println("Cont: " + contToMove.id + "; length: " + contToMove.length);
        for(int l=0; l<contToMove.length; l++){
            currentPosition.get(contToMove.slotId+l).pop();
            System.out.println("Cont: " + contToMove.id + " moved from: " + (contToMove.slotId+l) + " to: " + (destSlot+l));
            currentPosition.get(destSlot+l).add(contToMove);
        }
        contToMove.slotId = destSlot;
    }

    // Houdt geen rekening met midden van container. Neemt de container op aan meest linkse slot
    public void calcCraneMovement(Container contToMove, int startSlot, int destSlot){
        int rightX = Math.max(slots[startSlot].coordinate.x, slots[destSlot].coordinate.x);
        int leftX = Math.max(slots[startSlot].coordinate.x, slots[destSlot].coordinate.x);

        int upY = Math.max(slots[startSlot].coordinate.y, slots[destSlot].coordinate.y);
        int downY = Math.max(slots[startSlot].coordinate.y, slots[destSlot].coordinate.y);

        Crane craneToUse = null;
        int closestDist = Integer.MAX_VALUE;

        // Juiste kraan selecteren
        for(Crane crane : cranes){
            int tempClosestDist = Math.abs(crane.currentPos.x-slots[startSlot].coordinate.x)
                    + Math.abs(crane.currentPos.y-slots[startSlot].coordinate.y);
            if(tempClosestDist < closestDist){
                if(crane.xmax >= rightX && crane.xmin <= leftX
                && crane.ymax >= upY && crane.ymin <= downY){
                    craneToUse = crane;
                    closestDist = tempClosestDist;
                }
            }
        }

        // Safety distance check
        // Kranen kunnen enkel in de buurt komen via x-as
        for(Crane crane : cranes){
            if(!crane.equals(craneToUse)){

                // 1ste optie als startSlot.x < destSlot.x
                // 2de optie als startSlot.x > destSlot.x
                if((crane.currentPos.x > slots[startSlot].coordinate.x+1 && crane.currentPos.x < slots[destSlot].coordinate.x+1)
                || (crane.currentPos.x < slots[startSlot].coordinate.x+1 && crane.currentPos.x > slots[destSlot].coordinate.x+1)){
                    int startX = crane.currentPos.x;
                    int startY = crane.currentPos.y;
                    int startTime = time;
                    int endTime;
                    if(crane.currentPos.x < craneToUse.currentPos.x){
                        crane.currentPos.x = leftX;
                        endTime = Math.abs(startX-leftX) * crane.xspeed;

                    } else {
                        crane.currentPos.x = rightX;
                        endTime = Math.abs(startX-rightX) * crane.xspeed;
                    }
                    traject.add(new Traject(crane.id, -1, startTime, endTime, startX, startY, crane.currentPos.x, crane.currentPos.y));
                }
            }
        }

        int startX = craneToUse.currentPos.x;
        int startY = craneToUse.currentPos.y;
        int startTime = time;


        // X en Y beweging ter gelijkertijd
        int xMovements = Math.abs(craneToUse.currentPos.x - slots[startSlot].coordinate.x)
                + Math.abs(craneToUse.currentPos.x - slots[startSlot].coordinate.x);
        int yMovements = Math.abs(craneToUse.currentPos.y - slots[startSlot].coordinate.y)
                + Math.abs(craneToUse.currentPos.y - slots[startSlot].coordinate.y);
        int endTime;
        if(xMovements > yMovements){
            endTime = time + (xMovements * craneToUse.xspeed);
        } else {
            endTime = time + (yMovements * craneToUse.xspeed);
        }
        craneToUse.currentPos.x = slots[destSlot].coordinate.x;
        craneToUse.currentPos.y = slots[destSlot].coordinate.y;
        traject.add(new Traject(craneToUse.id, contToMove.id, startTime, endTime, startX, startY, craneToUse.currentPos.x, craneToUse.currentPos.y));
        time = endTime;
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
