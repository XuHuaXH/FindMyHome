package FLAGCamp.FindMyHome.Util;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AddressCoordinateHelper {
    private static final String HOST = "https://maps.googleapis.com";
    private static final String PATH = "/maps/api/geocode/json";
    private static final String API_KEY = APIKeys.GMAP_GeoCode_API_KEY;

    public static double[] AddressToLatLong(String address){

        try {
            address = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String quary = String.format("address=%s&key=%s", address, API_KEY);
        String url = HOST + PATH + "?" + quary;
        StringBuilder responseBody = new StringBuilder();


        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("Sending requets to url: " + url);
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(responseBody.toString());
        if (!obj.isNull("results")) {
            JSONArray results = obj.getJSONArray("results");
            JSONObject firstResult = results.getJSONObject(0);
            JSONObject geometry = firstResult.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double lat =  (double)location.get("lat");
            double lng =  (double)location.get("lng");
            return new double[]{lat, lng};
        }

        return null;
    }

    public static void main(String[] args) {
        double[] searchResult = AddressToLatLong("Cupertino");
        System.out.println(searchResult[0]);
        System.out.println(searchResult[1]);
    }
}
