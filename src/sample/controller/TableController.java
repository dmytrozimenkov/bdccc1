package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.TableBuilder;
import sample.tables.data.Agents;
import sample.tables.data.Clients;

public class TableController {

    @FXML
    private TableView tableView;
    private TableColumn[] cols;
    private ObservableList data;
    private String tableName;

    public void add(){

    }

    public void remove() {

    }

    public void edit() {

    }

    public void buildTable(String tableName, int columns){
        this.tableName = tableName;
        switch(tableName){
            case "Agents":
                data = new Agents().getData();
                cols = new TableBuilder<Agents>().buildTable("Agents", 2);
                break;
            case "Clients":
                data = new Clients().getData();
                cols = new TableBuilder<Clients>().buildTable("Clients", 3);
                break;
            default:
                System.out.println("IDI NAHUY");
        }
        tableView.setItems(data);
        tableView.getColumns().addAll(cols);
    }
}