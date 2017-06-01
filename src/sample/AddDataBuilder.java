package sample;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.*;
import sample.addData.data.Data;
import sample.tables.data.Countries;

/**
 * Created by yolo on 01.06.17.
 */
public class AddDataBuilder
{

    public ArrayList<Control> buildFields(String dataFieldsName){
        ArrayList<Control> fields = new ArrayList<>();
        JSONArray fieldsArray = parseFields(dataFieldsName);
        String type;
        for(int i = 0; i < fieldsArray.length(); i++){
            type = fieldsArray.getJSONObject(i).getString("type");
            switch(type){
                case "label":
                    fields.add(new Label(fieldsArray.getJSONObject(i).getString("name")));
                    break;
                case "text_field":
                    fields.add(new TextField(fieldsArray.getJSONObject(i).getString("name")));
                    break;
                case "dropdown":
                    ChoiceBox choiceBox = new ChoiceBox();
                    choiceBox.getItems().addAll(new Data().getData(fieldsArray.getJSONObject(i).getString("name")).values());
                    fields.add(choiceBox);
                default:
                    System.out.println("ERROR");
            }
        }
        return fields;
    }

    public JSONArray parseFields(String dataFieldsName){
        try{
            String json = readFile("src/sample/addData/json/Fields" + dataFieldsName + ".json", StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(json);
            return obj.getJSONArray("fields");
        } catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static String readFile(String path, Charset encoding) throws IOException{
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
