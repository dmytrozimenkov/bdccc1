package sample.tables.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Agents extends Data{

    private SimpleStringProperty id;
    private SimpleStringProperty name;

    private ObservableList<Agents> data = FXCollections.observableArrayList();

    public Agents(){

    }

    public Agents(int ID, String Name){
        this.id = new SimpleStringProperty(Integer.toString(ID));
        this.name = new SimpleStringProperty(Name);
    }

    protected void getTableData(){
        DB db = new DB();
        db.openConnection();
        try {
            ResultSet rsTest = db.query("SELECT * FROM Agents");
            while (rsTest.next()) {
                data.add(new Agents(rsTest.getInt(1), rsTest.getString(2) + " " + rsTest.getString(3)));
            }
            rsTest.close();
            db.closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<Agents> getData(){
        getTableData();
        return data;
    }


    public String getId() {
        return id.get();
    }


    public String getName() {
        return name.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
