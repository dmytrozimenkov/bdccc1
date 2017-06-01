package sample.addData.data;

import sample.DB;

import java.sql.SQLException;

/**
 * Created by yolo on 01.06.17.
 */
public class FieldsCountries {
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
}
