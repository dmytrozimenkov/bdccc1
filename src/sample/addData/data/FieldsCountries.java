package sample.addData.data;

import sample.DB;

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

    public HashMap<Integer, String> getCountries(){
        HashMap<Integer, String> countries = new HashMap();
        DB db = new DB();
        db.openConnection();
        try{
            ResultSet rs = db.query("SELECT country_id, country_name FROM Countries");
            while(rs.next()){
                countries.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        db.closeConnection();
        return countries;
    }
}
