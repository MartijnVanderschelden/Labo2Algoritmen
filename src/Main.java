import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read JSON file
        JSONReader jsonReader = new JSONReader();
        Yard yard;

        // 2 inputFiles: create Yard as target output
//        yard = jsonReader.readInputFile("src/jsonfiles/TerminalA_20_10_3_2_160.json", "src/jsonfiles/targetTerminalA_20_10_3_2_160.json");
//        yard.getDifferences();
//        System.out.println("Containers to move: " + yard.containersToMove);
//        yard.getDestinationSlot();
//
//        yard.moveContainers();

//        yard.generateFeasibleInitialStack();

//         1 inputFile: change maxHeight of yard to targetHeight
        yard = jsonReader.readInputFile("src/jsonfiles/MH2Terminal_20_10_3_2_160.json");
        yard.getStacksTooHigh();
        System.out.println("Containers to move: " + yard.containersToMove);
        yard.lowerStacksToTargetHeight();
        System.out.println("Overgebleven containers to move: " + yard.containersToMove);
        OutputWriter output = new OutputWriter();

        // Write output
        output.writeOutput(yard, "output.csv");
    }
}