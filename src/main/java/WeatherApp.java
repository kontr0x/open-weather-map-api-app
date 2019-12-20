import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner userInput1 = new Scanner(System.in);
        int cityID = -1;
        System.out.printf("Type in city name > ");
        String input = userInput1.next();
        System.out.println();

        HashMap<Integer, JSONObject> citys = CityListHelper.findEntrys(input,"city.list.json");

        if(citys.size()!=0){
            if(citys.size()>1){
                cityID = CityListHelper.listEntrys(citys);
            }else{
                System.out.println("\n"+citys.get(1).get("name")+" has ID : "+citys.get(1).get("id"));
                cityID = Integer.parseInt(citys.get(1).get("id").toString());
            }
        }else{
            System.out.println("No match found");
        }
        userInput1.close();

        //http://api.openweathermap.org/data/2.5/forecast?id=2825297&APPID=bb4ce93b554eb1474eb6d652eb1a85ae&units=metric <-- Example Url for API call forecast
        //http://api.openweathermap.org/data/2.5/weather?id=2825297&APPID=bb4ce93b554eb1474eb6d652eb1a85ae&units=metric <-- Example Url for API call current data

        StringBuilder apiCall = new StringBuilder();
        String apiUrlForecast = "http://api.openweathermap.org/data/2.5/forecast";
        String apiUrlCurrentData = "http://api.openweathermap.org/data/2.5/weather";
        String apiKey = "bb4ce93b554eb1474eb6d652eb1a85ae";
        apiCall.append(apiUrlCurrentData);
        apiCall.append("?id="+cityID);
        apiCall.append("&APPID="+apiKey);
        apiCall.append("&units=metric");

        URL apiURL = new URL(apiCall.toString());

        JSONObject apiJSON = APIhandler.fetchAPIData(apiURL);

        System.out.println("Current temperature : "+((JSONObject) apiJSON.get("main")).get("temp")+"°");
    }
}
