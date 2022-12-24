public class Main {
    public static void main(String[] args) {
        // Read JSON file
        JSONReader jsonReader = new JSONReader();
        Yard yard;

        // 2 inputFiles: create Yard as target output
        //yard = jsonReader.readInputFile("src/jsonfiles/TerminalA_20_10_3_2_100.json", "src/jsonfiles/targetTerminalA_20_10_3_2_100.json");

        // 1 inputFile: change maxHeight of yard to targetHeight
        yard = jsonReader.readInputFile("src/jsonfiles/MH2Terminal_20_10_3_2_100.json");


        //System.out.println(yard);
    }
}