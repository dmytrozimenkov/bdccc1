package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DB;
import sample.WindowManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    private WindowManager wm = WindowManager.getInstance();

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    public void login_func()  throws Exception{
        DB db = new DB();
        db.openConnection();
        ResultSet rs = db.query("SELECT agent_id, password FROM Agents WHERE agent_id = '" + login.getText() + "' AND password = '" + password.getText() + "'");
        try{
            if (!rs.isBeforeFirst() )
                wm.createAlert("Ошибка входа", "Неправльный id или пароль", Alert.AlertType.ERROR);
            else {
                while (rs.next()) {
                    wm.createWindow("view/Window.fxml", "Window", 800, 600);
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                    wm.getStage("Вход").close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        db.closeConnection();
    }
}
