package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.controller.TableController;
import sample.controller.TestTableController;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class WindowManager {

    private static WindowManager instance;
    private HashMap<String, Stage> stages = new HashMap<>();

    public static WindowManager getInstance(){
        if(instance == null) instance = new WindowManager();
        return instance;
    }

    public int counter = 1;

    public void createWindow(String fxmlUrl, String title, int width, int height){
        Stage stage = new Stage();
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlUrl));
            stage.setScene(new Scene(parent, width, height));
            stage.setTitle(title);
            if(stages.containsKey(title))
                stages.put(title + counter++, stage);
            else {
                stages.put(title, stage);
                counter = 1;
            }
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                public void handle(WindowEvent we) {
                    stages.remove(title);
                }
            });
            stage.show();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void createTableWindow(String tableName, String title, int cols){
        Stage stage = new Stage();
        try{
            if(stages.containsKey(title))
                createAlert("Окно '" + title + "' уже открыто", "", Alert.AlertType.WARNING);
            else {
                FXMLLoader loader = new FXMLLoader();
                Parent parent = loader.load(getClass().getResource("view/TableWindow.fxml").openStream());
                stage.setScene(new Scene(parent, 400, 300));
                stage.setTitle(title);
                stages.put(title, stage);
                TableController ttc = loader.getController();
                ttc.buildTable(tableName, cols);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        stages.remove(title);
                    }
                });
                stage.show();
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void createAlert(String headerText, String contentText, Alert.AlertType at){
        Alert alert = new Alert(at);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    public HashMap<String, Stage> getStages(){
        return stages;
    }

    public Stage getStage(String title){
        return stages.get(title);
    }

    public void closeAll(){
        for(Stage stage : stages.values()){
            stage.close();
        }
        createWindow("view/Login.fxml", "Вход", 190, 125);
    }
}
