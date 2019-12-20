import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner userInput1 = new Scanner(System.in);
        int cityID = -1;
        boolean foundMatch = true;
        System.out.printf("Type in city name > ");
        String input = userInput1.next();
        System.out.println();

        HashMap<Integer, JSONObject> citys = CityListHelper.findEntrys(input,"city.list.json");

        if(citys.size()!=0){
            if(citys.size()>1){
                cityID = CityListHelper.listEntrys(citys);
            }else{
                cityID = Integer.parseInt(citys.get(1).get("id").toString());
            }
        }else{
            System.out.println("No match found");
            foundMatch=false;
        }
        userInput1.close();

        if(foundMatch){
            JSONObject apiJSON = APIhandler.fetchAPIData(APIhandler.GenerateAPIurl(cityID));
            System.out.println("\nCurrent temperature in "+input+" : "+((JSONObject) apiJSON.get("main")).get("temp")+"°");
        }
    }
}