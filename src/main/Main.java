package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Crane kraan1 = new Crane(4, 7, 500, 500, new Coordinate(14, 50));
        Crane kraan2 = new Crane(6, 10, 500, 500, new Coordinate(420, 210));

        List<Crane> kranen = new ArrayList<Crane>();
        kranen.add(kraan1);
        kranen.add(kraan2);

        System.out.println(kraan1);
        System.out.println(kraan2);

        kraan1.move(new Coordinate(78, 140), kranen);

        Yard yard = new Yard();
        readInputFile("jsonfiles/terminal_4_3.json", yard);

        yard.addCrane(kraan1);
        yard.addCrane(kraan2);
    }

    static private void readInputFile(String filePath, Yard yard) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object obj = jsonParser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;

            //Read name
            String name = (String) jsonObject.get("name");
            yard.name = name;

            //Read slots
            JSONArray slots = (JSONArray) jsonObject.get("slots");
            for(int i=0; i < slots.size(); i++){
                JSONObject slotsObj = (JSONObject) slots.get(i);

                int id = ((Long) slotsObj.get("id")).intValue();
                int x = ((Long) slotsObj.get("x")).intValue();
                int y = ((Long) slotsObj.get("y")).intValue();
                yard.addSlot(new Slot(id, new Coordinate(x, y)));
            }

            //Read Containers
            JSONArray containers = (JSONArray) jsonObject.get("containers");
            for(int i=0; i < containers.size(); i++){
                JSONObject containersObject = (JSONObject) containers.get(i);
                int id = ((Long) containersObject.get("id")).intValue();
                int length = ((Long) containersObject.get("length")).intValue();
                yard.addContainer(new Container(id, length));
            }

            //Read assignments

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}