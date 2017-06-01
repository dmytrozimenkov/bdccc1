package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import sample.DB;
import sample.TableBuilder;
import sample.WindowManager;
import sample.tables.data.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class TableController {

    @FXML
    private TableView tableView;
    private TableColumn[] cols;
    public ObservableList data;
    private String tableName;
    private WindowManager windowManager = WindowManager.getInstance();
    private boolean selectable;


    public void add(){
        windowManager.createAddDataWindow(tableName, "Добавить данные в " + tableName);
    }

    public void remove() {

    }

    public void edit() {

    }

    public void removeData(){
        tableView.getItems().clear();
        data.removeAll(data);
    }

    public void refresh(){
        tableView.getItems().clear();
        data.removeAll(data);
        buildTable(tableName, selectable);
        tableView.setItems(data);
    }

    public void filterTable(String filter){
        ObservableList newData = FXCollections.observableArrayList();
        switch (tableName){
            case "Cities":
                DB db = new DB();
                db.openConnection();
                ResultSet rs = db.query("SELECT country_id FROM Countries WHERE country_name = '" + filter + "'");
                try{
                    while(rs.next()){
                        data = new Cities().getFilteredData(rs.getInt(1));
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
                tableView.setItems(data);
                break;
            default:
                System.out.println("nononono");
        }
    }

    public void buildTable(String tableName, boolean selectable){
        this.tableName = tableName;
        this.selectable = selectable;
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
                break;
            case "Adress":
                data = new Adress().getData();
                cols = new TableBuilder<Adress>().buildTable("Adress", 4);
                break;
            case "Cities":
                data = new Cities().getData();
                cols = new TableBuilder<Cities>().buildTable("Cities", 3);
            default:
                System.out.println("IDI NAHUY");
        }
        if(selectable) setSelectable();
    }

    public void setSelectable(){
        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    switch(tableName){
                        case "Cities":
                            Cities city = (Cities) tableView.getSelectionModel().getSelectedItem();
                            ((NewFieldController) windowManager.getController("Добавить данные в Adress")).changeButton(3, city.getCity_name());
                            break;
                        default:
                            System.out.println("IDI NAHUY");
                    }

                }
            }
        });
    }

    public void setData(){
        tableView.setItems(data);
        tableView.getColumns().addAll(cols);
    }
}