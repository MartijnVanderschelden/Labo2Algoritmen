import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read JSON file
        JSONReader jsonReader = new JSONReader();
        Yard yard;

        String inputFile;
        String targetFile;

        if(args.length == 2){
            inputFile = args[0];
            targetFile = args[1];

            yard = jsonReader.readInputFile(inputFile, targetFile);
            yard.getDifferences();
            System.out.println("Containers to move: " + yard.containersToMove);
            yard.getDestinationSlot();
            yard.moveContainers();
            System.out.println("Remaining containers to move: " + yard.containersToMove);
        } else {
            inputFile = args[0];

            yard = jsonReader.readInputFile(inputFile);
            yard.getStacksTooHigh();
            System.out.println("Containers to move: " + yard.containersToMove);
            yard.lowerStacksToTargetHeight();
            System.out.println("Remaining containers to move: " + yard.containersToMove);
        }

        /////////////////////////////
        // Write output
        /////////////////////////////
        String outputName = FilenameUtils.removeExtension(inputFile);
        if(outputName.contains("/")){
            String[] split = outputName.split("/");
            outputName = split[split.length-1];
        } else if(outputName.contains("\\")){
            String[] split = outputName.split("\\\\");
            outputName = split[split.length-1];
        }

        OutputWriter output = new OutputWriter();
        output.writeOutput(yard, "output_" + outputName + ".csv");
    }
}