package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.TableBuilder;
import sample.WindowManager;
import sample.tables.data.Agents;
import sample.tables.data.Data;

import java.net.URL;
import java.util.ResourceBundle;

public class TestTableController {
    @FXML
    private TableView tableView;
    private WindowManager windowManager = WindowManager.getInstance();

    private ObservableList<?> data = FXCollections.observableArrayList();

    private String tableName;

    public void add(){
        windowManager.createAddDataWindow("date" + tableName, "Добавить данные в " + tableName);
    }

    public void remove() {

    }

    public void edit() {

    }

    public void buildTable(String tableName, int columns){
        this.tableName = tableName;
        tableView.getColumns().addAll(new TableBuilder().buildTable(tableName, columns));
    }
}