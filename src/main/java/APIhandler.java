import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
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
        }catch (UnknownHostException e){
            System.out.println("Host seams not to be reachable");
        }catch(NullPointerException e){
            System.out.println("Given URL is null");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static URL GenerateAPIurl(int cityID) throws MalformedURLException {

        //http://api.openweathermap.org/data/2.5/forecast?id=2825297&APPID=bb4ce93b554eb1474eb6d652eb1a85ae&units=metric <-- Example Url for API call forecast
        //http://api.openweathermap.org/data/2.5/weather?id=2825297&APPID=bb4ce93b554eb1474eb6d652eb1a85ae&units=metric <-- Example Url for API call current data

        try{
            if(cityID==-1){
                return new URL(""); //returning an empty URL
            }
            StringBuilder apiCall = new StringBuilder();
            String apiUrlForecast = "http://api.openweathermap.org/data/2.5/forecast";
            String apiUrlCurrentData = "http://api.openweathermap.org/data/2.5/weather";
            String apiKey = "bb4ce93b554eb1474eb6d652eb1a85ae";
            apiCall.append(apiUrlCurrentData);
            apiCall.append("?id="+cityID);
            apiCall.append("&APPID="+apiKey);
            apiCall.append("&units=metric");
            return new URL(apiCall.toString());
        }catch(NullPointerException e){
            System.out.println("Object is null");
        }catch (Exception e){
            System.out.println("An error occurred");
        }
        return new URL(""); //returning an empty URL
    }
}
