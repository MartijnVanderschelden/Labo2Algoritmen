import java.util.*;

public class Traject {
    int craneId;
    int containerId;
    int pickupTime;
    int endTime;
    int pickupX;
    int pickupY;
    int endPosX;
    int endPosY;

    public Traject(int craneId, int containerId, int pickupTime, int endTime, int pickupX, int pickupY, int endPosX, int endPosY) {
        this.craneId = craneId;
        this.containerId = containerId;
        this.pickupTime = pickupTime;
        this.endTime = endTime;
        this.pickupX = pickupX;
        this.pickupY = pickupY;
        this.endPosX = endPosX;
        this.endPosY = endPosY;
    }

    @Override
    public String toString() {
        return "Traject{" +
                "craneId=" + craneId +
                ", containerId=" + containerId +
                ", pickupTime=" + pickupTime +
                ", endTime=" + endTime +
                ", pickupX=" + pickupX +
                ", pickupY=" + pickupY +
                ", endPosX=" + endPosX +
                ", endPosY=" + endPosY +
                '}';
    }
}
