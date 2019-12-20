import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONhandler {
    public static JSONArray loadJSON(String fileName) throws IOException, ParseException {
        try{
            Object objJSON = new JSONParser().parse(new FileReader(fileName)); //parsing the read file thought the filereader as an JSON format and get stored as an object temporally
            return (JSONArray) objJSON; //returning the fully scanned JSON file as an JSON array this is caused thought the format of the city list
        }catch(FileNotFoundException e){
            System.out.println("ERROR : File not found");
        }
        return null; //returning null if exception appeared
    }
}
