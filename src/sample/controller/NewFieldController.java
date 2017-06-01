package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.AddDataBuilder;
import sample.WindowManager;
import sample.addData.data.FieldsClients;
import sample.addData.data.FieldsCountries;
import sample.tables.data.Countries;

import java.util.ArrayList;

public class NewFieldController {

    @FXML
    private VBox fieldsVbox;

    private AddDataBuilder dataBuilder = new AddDataBuilder();
    private ArrayList<Control> fields;
    private String dataFieldsName;

    private WindowManager wm = WindowManager.getInstance();

    public void add(){
        switch(dataFieldsName){
            case "Countries":
                TextField country_code = (TextField) fieldsVbox.getChildren().get(1);
                TextField country_name = (TextField) fieldsVbox.getChildren().get(3);
                new FieldsCountries().addData(Integer.parseInt(country_code.getText()), country_name.getText());
                break;
            case "Clients":
                break;
            default:
                System.out.println("IDI NAHUY");
        }
        Stage stage = (Stage) fieldsVbox.getScene().getWindow();
        wm.removeStage(stage.getTitle());
        stage.close();
    }

    public void createFields(String dataFieldsName){
        this.dataFieldsName = dataFieldsName;
        fields = dataBuilder.buildFields(dataFieldsName);
        fieldsVbox.getChildren().addAll(fields);
    }
}
