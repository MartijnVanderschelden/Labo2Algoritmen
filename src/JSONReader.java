import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.io.FilenameUtils;

import java.io.FileReader;

public class JSONReader {

    // 2 inputFiles: create Yard as target output
    public Yard readInputFile(String filePathInput, String filePathTarget) {
        String[] filenameArr = filePathInput.split("/");
        String filename = FilenameUtils.removeExtension(filenameArr[2]);
        String[] stringArr = filename.split("_");

        JSONParser jsonParser = new JSONParser();
        Yard yard = new Yard();

        // Start setup
        try {
            Object obj = jsonParser.parse(new FileReader(filePathInput));

            JSONObject jsonObject = (JSONObject) obj;

            //Read name
            yard.name = (String) jsonObject.get("name");

            //Read length yard
            yard.length = ((Long) jsonObject.get("length")).intValue();

            //Read width yard
            yard.width = ((Long) jsonObject.get("width")).intValue();

            //Read maxheight
            yard.maxHeight = ((Long) jsonObject.get("maxheight")).intValue();

            yard.createArrays(Integer.parseInt(stringArr[1])*Integer.parseInt(stringArr[2]),
                    Integer.parseInt(stringArr[5]));

            //Read slots
            JSONArray slots = (JSONArray) jsonObject.get("slots");
            for(int i=0; i < slots.size(); i++){
                JSONObject slotsObj = (JSONObject) slots.get(i);

                int id = ((Long) slotsObj.get("id")).intValue();
                int x = ((Long) slotsObj.get("x")).intValue();
                int y = ((Long) slotsObj.get("y")).intValue();
                yard.addSlot(new Slot(id, new Coordinate(x, y)));
            }

            //Read cranes
            JSONArray cranes = (JSONArray) jsonObject.get("cranes");
            for(int i=0; i < cranes.size(); i++){
                JSONObject cranesObj = (JSONObject) cranes.get(i);

                double x = ((Number)  cranesObj.get("x")).doubleValue();
                double y = ((Number)  cranesObj.get("y")).doubleValue();
                int ymin = ((Long) cranesObj.get("ymin")).intValue();
                int ymax = ((Long) cranesObj.get("ymax")).intValue();
                int id = ((Long) cranesObj.get("id")).intValue();
                int xspeed = ((Long) cranesObj.get("xspeed")).intValue();
                int yspeed = ((Long) cranesObj.get("yspeed")).intValue();
                int xmin = ((Long) cranesObj.get("xmin")).intValue();
                int xmax = ((Long) cranesObj.get("xmax")).intValue();

                yard.addCrane(new Crane(id, x, y, xmin, xmax, ymin, ymax, xspeed, yspeed));
            }

            //Read Containers
            JSONArray containers = (JSONArray) jsonObject.get("containers");
            for(int i=0; i < containers.size(); i++){
                JSONObject containersObject = (JSONObject) containers.get(i);
                int id = ((Long) containersObject.get("id")).intValue();
                int lengthContainer = ((Long) containersObject.get("length")).intValue();
                yard.addContainer(new Container(id, lengthContainer));
            }

            //Read assignments
            JSONArray assignments = (JSONArray) jsonObject.get("assignments");
            for(int i=0; i < assignments.size(); i++){
                JSONObject assignmentObject = (JSONObject) assignments.get(i);
                int slot_id = ((Long) assignmentObject.get("slot_id")).intValue();
                int container_id = ((Long) assignmentObject.get("container_id")).intValue();
                Container container = yard.containers[container_id];

                yard.slots[slot_id].containerStack.add(container);

                int heightContainer = yard.slots[slot_id].getHeight();
                yard.containers[container_id].changePosition(slot_id, heightContainer);
                //System.out.println(heightContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Target
        try {
            Object obj = jsonParser.parse(new FileReader(filePathTarget));
            JSONObject jsonObject = (JSONObject) obj;

            //Read targetMaxHeight
            yard.targetHeight = ((Long) jsonObject.get("maxheight")).intValue();


            //Read target assignments
            JSONArray targetAssignments = (JSONArray) jsonObject.get("assignments");
            for(int i=0; i < targetAssignments.size(); i++){
                JSONObject slotsObj = (JSONObject) targetAssignments.get(i);

                int slot_id = ((Long) slotsObj.get("slot_id")).intValue();
                int container_id = ((Long) slotsObj.get("container_id")).intValue();
                yard.addTargetPosition(container_id, slot_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yard;
    }

    // 1 inputFile: change maxHeight of yard to targetHeight
    public Yard readInputFile(String filePathInput) {
        String[] filenameArr = filePathInput.split("/");
        String filename = FilenameUtils.removeExtension(filenameArr[2]);
        String[] stringArr = filename.split("_");

        JSONParser jsonParser = new JSONParser();
        Yard yard = new Yard();

        // Start setup
        try {
            Object obj = jsonParser.parse(new FileReader(filePathInput));

            JSONObject jsonObject = (JSONObject) obj;

            //Read name
            yard.name = (String) jsonObject.get("name");

            //Read length yard
            yard.length = ((Long) jsonObject.get("length")).intValue();

            //Read width yard
            yard.width = ((Long) jsonObject.get("width")).intValue();

            //Read maxheight
            yard.maxHeight = ((Long) jsonObject.get("maxheight")).intValue();

            //Read targetMaxHeight
            yard.targetHeight = ((Long) jsonObject.get("targetheight")).intValue();

            yard.createArrays(Integer.parseInt(stringArr[1])*Integer.parseInt(stringArr[2]),
                    Integer.parseInt(stringArr[5]));

            //Read slots
            JSONArray slots = (JSONArray) jsonObject.get("slots");
            for (int i = 0; i < slots.size(); i++) {
                JSONObject slotsObj = (JSONObject) slots.get(i);

                int id = ((Long) slotsObj.get("id")).intValue();
                int x = ((Long) slotsObj.get("x")).intValue();
                int y = ((Long) slotsObj.get("y")).intValue();
                yard.addSlot(new Slot(id, new Coordinate(x, y)));
            }

            //Read cranes
            JSONArray cranes = (JSONArray) jsonObject.get("cranes");
            for (int i = 0; i < cranes.size(); i++) {
                JSONObject cranesObj = (JSONObject) cranes.get(i);

                double x = ((Number) cranesObj.get("x")).doubleValue();
                double y = ((Number) cranesObj.get("y")).doubleValue();
                int ymin = ((Long) cranesObj.get("ymin")).intValue();
                int ymax = ((Long) cranesObj.get("ymax")).intValue();
                int id = ((Long) cranesObj.get("id")).intValue();
                int xspeed = ((Long) cranesObj.get("xspeed")).intValue();
                int yspeed = ((Long) cranesObj.get("yspeed")).intValue();
                int xmin = ((Long) cranesObj.get("xmin")).intValue();
                int xmax = ((Long) cranesObj.get("xmax")).intValue();

                yard.addCrane(new Crane(id, x, y, xmin, xmax, ymin, ymax, xspeed, yspeed));
            }

            //Read Containers
            JSONArray containers = (JSONArray) jsonObject.get("containers");
            for (int i = 0; i < containers.size(); i++) {
                JSONObject containersObject = (JSONObject) containers.get(i);
                int id = ((Long) containersObject.get("id")).intValue();
                int lengthContainer = ((Long) containersObject.get("length")).intValue();
                yard.addContainer(new Container(id, lengthContainer));
            }

            //Read assignments
            JSONArray assignments = (JSONArray) jsonObject.get("assignments");
            for (int i = 0; i < assignments.size(); i++) {
                JSONObject assignmentObject = (JSONObject) assignments.get(i);
                int slot_id = ((Long) assignmentObject.get("slot_id")).intValue();
                int container_id = ((Long) assignmentObject.get("container_id")).intValue();
                Container container = yard.containers[container_id];

                yard.slots[slot_id].containerStack.add(container);

                int heightContainer = yard.slots[slot_id].getHeight();
                yard.containers[container_id].changePosition(slot_id, heightContainer);
                //System.out.println(heightContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yard;
    }
}
