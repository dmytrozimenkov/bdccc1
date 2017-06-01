package sample.addData.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yolo on 01.06.17.
 */
public class Data {
    public class SubKey {
        int key1;
        int key2;

        public SubKey(int key1){
            this.key1 = key1;
        }

        public SubKey(int key1, int key2){
            this.key1 = key1;
            this.key2 = key2;
        }

        public int getKey1(){
            return key1;
        }

        public int getKey2(){
            return key2;
        }
    }

    private HashMap<SubKey, String> data = new HashMap<>();
    protected int sosihuy;

    public HashMap<SubKey, String> getData(String name){
        switch(name){
            case "Страна":
                FieldsCountries fc = new FieldsCountries();
                for(int i = 0; i < fc.getCountries().size(); i++){
                    data.put(new SubKey(Integer.parseInt(fc.getCountries().get(i).getCountry_code())),
                                        fc.getCountries().get(i).getCountry_name());
                }
                break;
            case "Город":
                FieldsCities fcities = new FieldsCities();
                for(int i = 0; i < fcities.getCities().size(); i++) {
                    data.put(new SubKey(Integer.parseInt(fcities.getCities().get(i).getCity_id()),
                                        Integer.parseInt(fcities.getCities().get(i).getCountry_id())),
                                        fcities.getCities().get(i).getCity_name());
                }
                break;
            default:
                System.out.println("PARASHA");
        }
        return data;
    }

    public void clearData(){
        data.clear();
    }

}
