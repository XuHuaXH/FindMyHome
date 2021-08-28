package src.FindMyHome;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class AddressDataLoader {
    private static final String BASE_URL = "http://localhost:8080";
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static String readFile() throws IOException {

        String file = "src/main/resources/static/data/addresses-us-all.json";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder bd = new StringBuilder();
        String currentLine = reader.readLine();
        while (currentLine != null) {
            bd.append(currentLine);
            bd.append("\n");
            currentLine = reader.readLine();
        }

        reader.close();
        return bd.toString();
    }

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();

        String json = readFile();
        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(BASE_URL + "/data-import")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body() == null ? "" : response.body().string());
    }
}
