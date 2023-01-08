import java.io.*;

public class OutputWriter {
    public void writeOutput(Yard yard, String outputFileName) throws IOException {
        File outputFile = new File("outputFiles/" + outputFileName);
        FileOutputStream ofs = new FileOutputStream(outputFile);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ofs));

        bw.write("%CraneId;ContainerId;PickupTime;EndTime;PickupPosX;PickupPosY;EndPosX;EndPosY");
        bw.newLine();
        for(Traject t : yard.traject){
            String containerId = String.valueOf(t.containerId);
            if(t.containerId == -1){
                containerId = "";
            }
            String outputLine = t.craneId + ";" + containerId
                    + ";" + t.pickupTime + ";" + t.endTime + ";" + t.pickupX
                    + ";" + t.pickupY + ";" + t.endPosX + ";" + t.endPosY;
            bw.write(outputLine);
            bw.newLine();
        }
        bw.close();
    }
}
