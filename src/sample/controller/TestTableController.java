package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.TableBuilder;
import sample.tables.data.Agents;
import sample.tables.data.Data;

import java.net.URL;
import java.util.ResourceBundle;

public class TestTableController {
    @FXML
    private TableView tableView;

    private ObservableList<?> data = FXCollections.observableArrayList();

    public void add(){

    }

    public void remove() {

    }

    public void edit() {

    }

    public void buildTable(String tableName, int columns){
        tableView.getColumns().addAll(new TableBuilder().buildTable(tableName, columns));
    }
}