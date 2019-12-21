import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        String city;
        int cityID;
        Scanner userInput1 = new Scanner(System.in);

        if(args.length!=0){
            city=args[0];
        }else{
            System.out.printf("Type in city name > ");
            city = userInput1.next();
            System.out.println();
        }
        HashMap<Integer, JSONObject> citys = CityListHelper.findEntrys(city,"city.list.json");

        cityID = CityListHelper.listEntrys(citys);

        userInput1.close();

        if(cityID>-1){
            JSONObject apiJSON = APIhandler.fetchAPIData(APIhandler.GenerateAPIurl(cityID));
            APIhandler.printForecast(apiJSON);
        }
    }
}