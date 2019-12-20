import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIhandler {
    public static JSONObject fetchAPIData(URL url){
        try {
            HttpURLConnection apiConn = (HttpURLConnection)url.openConnection();
            apiConn.setRequestMethod("GET");
            apiConn.connect();
            String stringJSON = "";
            Scanner scan1 = new Scanner(url.openStream());
            while (scan1.hasNext()){
                stringJSON+=scan1.nextLine();
            }
            scan1.close();
            JSONParser parser = new JSONParser();
            return (JSONObject)parser.parse(stringJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
