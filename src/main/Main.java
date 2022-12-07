package main;

public class Main {
    public static void main(String[] args) {
        // Read JSON file
        JSONReader jsonReader = new JSONReader();
        Yard yard = jsonReader.readInputFile("src/main/jsonfiles/terminal22_1_100_1_10.json", "src/main/jsonfiles/terminal22_1_100_1_10target.json");

        System.out.println(yard);
    }
}