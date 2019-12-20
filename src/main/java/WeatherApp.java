import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        String city;
        int cityID = -1;
        boolean foundMatch = true;
        Scanner userInput1 = new Scanner(System.in);

        if(args.length!=0){
            city=args[0];
        }else{
            System.out.printf("Type in city name > ");
            city = userInput1.next();
            System.out.println();
        }
        HashMap<Integer, JSONObject> citys = CityListHelper.findEntrys(city,"city.list.json");

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

            String input_date = "yyyy-MM-dd";
            SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
            Date dt1;
            DateFormat format2=new SimpleDateFormat("EEEE");
            String finalDay;

            for (Object jsonOBJ : (JSONArray)apiJSON.get("list")) {
                if(jsonOBJ instanceof JSONObject){
                    if(!input_date.equals(((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[0])){
                        input_date=((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[0];
                        dt1=format1.parse(input_date);
                        finalDay=format2.format(dt1);
                        System.out.println("\n"+finalDay+" "+((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[0].split("-")[2]+"."+((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[0].split("-")[1]);
                    }
                    System.out.println("\t"+((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[1].split(":")[0]+":"+((JSONObject) jsonOBJ).get("dt_txt").toString().split("\\s+")[1].split(":")[1]+": "+((JSONObject) ((JSONObject) jsonOBJ).get("main")).get("temp")+"°C , "+((JSONObject)((JSONArray)((JSONObject) jsonOBJ).get("weather")).get(0)).get("description"));
                }
            }
        }
    }
}