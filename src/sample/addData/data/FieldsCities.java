package sample.addData.data;

import sample.DB;
import sample.tables.data.Cities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yolo on 01.06.17.
 */
public class FieldsCities {
    public ArrayList<Cities> getCities(){
        ArrayList<Cities> cities = new ArrayList<>();
        DB db = new DB();
        db.openConnection();
        try{
            ResultSet rs = db.query("SELECT city_id, country_id, city_name, city_type_id FROM Cities");
            while(rs.next()){
                cities.add(new Cities(Integer.toString(rs.getInt(1)),
                        Integer.toString(rs.getInt(2)),
                        rs.getString(3),
                        Integer.toString(4)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return cities;
    }

}
