package sample.addData.data;

import sample.DB;
import sample.tables.data.Countries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yolo on 01.06.17.
 */
public class FieldsCountries extends Data{
    public void addData(int country_code, String name){
        DB db = new DB();
        db.openConnection();
        try{
            db.ins_query("INSERT INTO Countries VALUES (null," + country_code + ", '" + name + "');");
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        db.closeConnection();
    }

    public ArrayList<Countries> getCountries(){
        ArrayList<Countries> countries = new ArrayList<>();
        DB db = new DB();
        db.openConnection();
        try{
            ResultSet rs = db.query("SELECT country_id, country_code, country_name FROM Countries");
            while(rs.next()){
                countries.add(new Countries(Integer.toString(rs.getInt(1)),
                                            Integer.toString(rs.getInt(2)),
                                            rs.getString(3)));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        db.closeConnection();
        return countries;
    }
}
