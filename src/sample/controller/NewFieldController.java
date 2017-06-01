package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.AddDataBuilder;
import sample.DB;
import sample.WindowManager;
import sample.addData.data.FieldsClients;
import sample.addData.data.FieldsCountries;
import sample.tables.data.Countries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewFieldController {

    @FXML
    private VBox fieldsVbox;

    private AddDataBuilder dataBuilder = new AddDataBuilder();
    private ArrayList<Control> fields;
    private String dataFieldsName;
    private String choiceBoxValue;

    private WindowManager wm = WindowManager.getInstance();

    public void changeButton(int id, String value){
        ((Button) fieldsVbox.getChildren().get(id)).setText(value);
    }

    public void setChoiceBoxValue(String choiceBoxValue){
        this.choiceBoxValue = choiceBoxValue;
    }

    public void createTableWindow(String type){
        wm.createTableWindow(type, type, true);
        if(choiceBoxValue != null){
            ((TableController) wm.getController(type)).filterTable(choiceBoxValue);
        }
    }

    public void add(){
        switch(dataFieldsName){
            case "Countries":
                TextField country_code = (TextField) fieldsVbox.getChildren().get(1);
                TextField country_name = (TextField) fieldsVbox.getChildren().get(3);
                new FieldsCountries().addData(Integer.parseInt(country_code.getText()), country_name.getText());
                break;
            case "Clients":
                break;
            case "Adress":
                ChoiceBox country_cb = (ChoiceBox) fieldsVbox.getChildren().get(1);

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
