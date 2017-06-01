package sample.addData.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yolo on 01.06.17.
 */
public class Data {
    private HashMap<Integer, String> data = new HashMap<>();
    protected int sosihuy;

    public HashMap<Integer, String> getData(String name){
        switch(name){
            case "Страна":
                data.putAll(new FieldsCountries().getCountries());
                break;
            case "Город":

            default:
                System.out.println("PARASHA");
        }
        return data;
    }

    public void clearData(){
        data.clear();
    }

}
