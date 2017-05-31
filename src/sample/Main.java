package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private WindowManager wm = WindowManager.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        wm.createWindow("view/Login.fxml", "Вход", 190, 125);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
