package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.TableBuilder;
import sample.WindowManager;
import sample.tables.data.Adress;
import sample.tables.data.Agents;
import sample.tables.data.Clients;
import sample.tables.data.Countries;

public class TableController {

    @FXML
    private TableView tableView;
    private TableColumn[] cols;
    public ObservableList data;
    private String tableName;
    private WindowManager windowManager = WindowManager.getInstance();



    public void add(){
        windowManager.createAddDataWindow(tableName, "Добавить данные в " + tableName);
    }

    public void remove() {

    }

    public void edit() {

    }

    public void refresh(){
        tableView.getItems().clear();
        data.removeAll(data);
        buildTable(tableName);
        tableView.setItems(data);
    }


    public void buildTable(String tableName){
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
            case "Countries":
                data = new Countries().getData();
                cols = new TableBuilder<Countries>().buildTable("Countries", 2);
            case "Adress":
                data = new Adress().getData();
                cols = new TableBuilder<Adress>().buildTable("Adress", 4);
            default:
                System.out.println("IDI NAHUY");
        }
    }

    public void setData(){
        tableView.setItems(data);
        tableView.getColumns().addAll(cols);
    }
}