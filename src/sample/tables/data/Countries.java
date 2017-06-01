package sample.tables.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yolo on 01.06.17.
 */
public class Countries extends Data{
    private SimpleStringProperty country_code;
    private SimpleStringProperty country_name;
    private SimpleStringProperty country_id;

    private ObservableList<Countries> data = FXCollections.observableArrayList();

    public Countries(){

    }

//    public Countries(String country_code, String country_name){
//        this.country_code = new SimpleStringProperty(country_code);
//        this.country_name = new SimpleStringProperty(country_name);
//    }

    public Countries(String country_id, String country_code, String country_name){
        this.country_id = new SimpleStringProperty(country_id);
        this.country_code = new SimpleStringProperty(country_code);
        this.country_name = new SimpleStringProperty(country_name);
    }

    protected void getTableData(){
        DB db = new DB();
        db.openConnection();
        try{
            ResultSet rs = db.query("SELECT * FROM Countries");
            while (rs.next()){
                data.add(new Countries(Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)), rs.getString(3)));
            }
            rs.close();
            db.closeConnection();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public ObservableList<Countries> getData(){
        getTableData();
        return data;
    }

    public String getCountry_code() {
        return country_code.get();
    }

    public SimpleStringProperty country_codeProperty() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code.set(country_code);
    }

    public String getCountry_name() {
        return country_name.get();
    }

    public SimpleStringProperty country_nameProperty() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name.set(country_name);
    }
}
