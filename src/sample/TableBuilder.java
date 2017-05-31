package sample;

import com.sun.tools.javac.util.Name;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.tables.data.Data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class TableBuilder <T extends Data> {

    private HashMap<Integer, TableColumn> columnList = new HashMap<>();

    public TableColumn<T, String>[] buildTable(String tableName, int columns){
        JSONArray colArray = parseTable(tableName);
        TableColumn<T, String>[] cols = new TableColumn[columns];
        for(int i = 0; i < columns; i++){
            TableColumn col = new TableColumn(colArray.getJSONObject(i).getString("Title"));
            col.setCellValueFactory(new PropertyValueFactory<T, String>(colArray.getJSONObject(i).getString("Name")));
            cols[i] = col;
        }
        return cols;
    }

    private JSONArray parseTable(String tableName){
        try {
            String json = readFile("src/sample/tables/json/" + tableName + ".json", StandardCharsets.UTF_8);
            JSONObject obj = new JSONObject(json);
            return obj.getJSONArray("columns");
        } catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
