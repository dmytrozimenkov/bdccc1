package sample.tables.data;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by yolo on 01.06.17.
 */
public class Cities {

    private SimpleStringProperty city_id;
    private SimpleStringProperty country_id;
    private SimpleStringProperty city_name;
    private SimpleStringProperty city_type_id;

    public Cities(){

    }

    public Cities(String city_id, String country_id, String city_name, String city_type){
        this.city_id = new SimpleStringProperty(city_id);
        this.country_id = new SimpleStringProperty(country_id);
        this.city_name = new SimpleStringProperty(city_name);
        this.city_type_id = new SimpleStringProperty(city_type);
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

    public String getCountry_id() {
        return country_id.get();
    }

    public SimpleStringProperty country_idProperty() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id.set(country_id);
    }

    public String getCity_name() {
        return city_name.get();
    }

    public SimpleStringProperty city_nameProperty() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name.set(city_name);
    }

    public String getCity_type_id() {
        return city_type_id.get();
    }

    public SimpleStringProperty city_type_idProperty() {
        return city_type_id;
    }

    public void setCity_type_id(String city_type_id) {
        this.city_type_id.set(city_type_id);
    }
}
