import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read JSON file
        JSONReader jsonReader = new JSONReader();
        Yard yard;

        //////////////////////////////
        // 2 inputFiles: create Yard as target output
        /////////////////////////////
//        String inputFile = "Terminal_10_10_3_1_100";
//        String targetFile = "targetTerminal_10_10_3_1_100";
//        String inputFile = "TerminalA_20_10_3_2_100";
//        String targetFile = "targetTerminalA_20_10_3_2_100";
//        String inputFile = "TerminalA_20_10_3_2_160";
//        String targetFile = "targetTerminalA_20_10_3_2_160";
//        String inputFile = "TerminalB_20_10_3_2_160";
//        String targetFile = "targetTerminalB_20_10_3_2_160";
//        String inputFile = "TerminalC_10_10_3_2_80";
//        String targetFile = "targetTerminalC_10_10_3_2_80";
//        String inputFile = "TerminalD_10_10_3_2_80";
//        String targetFile = "targetTerminalD_10_10_3_2_80";
//        String inputFile = "TerminalE_10_10_3_2_100";
//        String targetFile = "targetTerminalE_10_10_3_2_100";
//        String inputFile = "TerminalF_10_10_3_2_100";
//        String targetFile = "targetTerminalF_10_10_3_2_100";

//        yard = jsonReader.readInputFile("src/jsonfiles/" + inputFile + ".json", "src/jsonfiles/" + targetFile + ".json");
//        yard.getDifferences();
//        System.out.println("Containers to move: " + yard.containersToMove);
//        yard.getDestinationSlot();
//        yard.moveContainers();
//        System.out.println("Remaining containers to move: " + yard.containersToMove);

        //////////////////////////////
        // 1 inputFile: change maxHeight of yard to targetHeight
        /////////////////////////////
//        String inputFile = "MH2Terminal_20_10_3_2_100";
        String inputFile = "MH2Terminal_20_10_3_2_160";
        yard = jsonReader.readInputFile("src/jsonfiles/" + inputFile + ".json");
        yard.getStacksTooHigh();
        System.out.println("Containers to move: " + yard.containersToMove);
        yard.lowerStacksToTargetHeight();
        System.out.println("Remaining containers to move: " + yard.containersToMove);

        /////////////////////////////
        // Write output
        /////////////////////////////
        OutputWriter output = new OutputWriter();
        output.writeOutput(yard, "output_" + inputFile + ".csv");
    }
}