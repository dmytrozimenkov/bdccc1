package sample;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import org.json.*;
import sample.addData.data.Data;
import sample.controller.NewFieldController;
import sample.controller.TableController;
import sample.tables.data.Countries;

/**
 * Created by yolo on 01.06.17.
 */
public class AddDataBuilder
{

    private WindowManager wm = WindowManager.getInstance();

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
                    choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                            ((NewFieldController) wm.getController("Добавить данные в " + dataFieldsName)).setChoiceBoxValue((String) choiceBox.getItems().get((Integer) number2));                     }
                    });
                    fields.add(choiceBox);
                    break;
                case "table-selector-button":
                    JSONObject obj = fieldsArray.getJSONObject(i);
                    Button button = new Button("Выберите " + fieldsArray.getJSONObject(i).getString("name"));
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ((NewFieldController) wm.getController("Добавить данные в " + dataFieldsName)).createTableWindow(obj.getString("action"));
                        }
                    });
                    fields.add(button);
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
