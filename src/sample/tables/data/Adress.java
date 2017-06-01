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
public class Adress extends Data{
    private SimpleStringProperty country_id;
    private SimpleStringProperty city_id;
    private SimpleStringProperty street_id;
    private SimpleStringProperty house_id;

    private static ObservableList<Adress> data = FXCollections.observableArrayList();

    public Adress(){

    }

    public Adress(String country_id, String city_id, String street_id, String house_id){
        this.country_id = new SimpleStringProperty(country_id);
        this.city_id = new SimpleStringProperty(city_id);
        this.street_id = new SimpleStringProperty(street_id);
        this.house_id = new SimpleStringProperty(house_id);
    }


    public void getTableData(){
        DB db = new DB();
        db.openConnection();
        try{
            ResultSet rs = db.query("SELECT * FROM Adress");
            while(rs.next()){
                ResultSet newrs = db.secondQuery("SELECT country_name, city_name, street_name, house_number " +
                                            "FROM Countries, Cities, Streets, Houses WHERE\n" +
                                            " Countries.country_id = " + rs.getInt(2) + " AND " +
                                            " Cities.city_id = " + rs.getInt(3) + " AND " +
                                            " Streets.street_id = " + rs.getInt(4) +
                                            " AND Houses.house_id = " + rs.getInt(5));
                while(newrs.next()) {
                    data.add(new Adress(newrs.getString(1), newrs.getString(2), newrs.getString(3), newrs.getString(4)));
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public ObservableList<Adress> getData(){
        getTableData();
        return data;
    }

    public String getCountry_id() {
        return country_id.get();
    }

    public SimpleStringProperty country_idProperty() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id.set(country_id);
    }

    public String getCity_id() {
        return city_id.get();
    }

    public SimpleStringProperty city_idProperty() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id.set(city_id);
    }

    public String getStreet_id() {
        return street_id.get();
    }

    public SimpleStringProperty street_idProperty() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id.set(street_id);
    }

    public String getHouse_id() {
        return house_id.get();
    }

    public SimpleStringProperty house_idProperty() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id.set(house_id);
    }
}
