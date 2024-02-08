
import com.google.gson.*;
import lombok.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MovieFetch {
    // Show how to read Rest API Json into a DTO or a JsonObject
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        MovieFetch mf = new MovieFetch();
        mf.getResponseBody2("https://jsonplaceholder.typicode.com/todos/1");
        String key = System.getenv("WEATHER_MAP_KEY");
        GeoLocationDTO location = mf.getLocation("Lyngby", key); // Get location from city name. Format: [{"name":"Kongens Lyngby","local_names":{"lt":"Liungbiu","da":"Kongens Lyngby"},"lat":55.7718649,"lon":12.5051413,"country":"DK","state":"Capital Region of Denmark"}]

        // With DTOs:
        WeatherDataDTO wdd = mf.getCurrentWeather(location.getLat(), location.getLon(), key);
        System.out.println("Temperature: " + wdd.getMain().getTemp());
        System.out.println("Weather Description: " + wdd.getWeather()[0].getDescription());

        // With JsonObject:
        JsonObject locationWithJsonObject = mf.getWeatherWithJsonObject(location.getLat(), location.getLon(), key);

        System.out.println("Temperature from JsonObject: " + locationWithJsonObject.get("temperature"));
    }

    private String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            System.out.println();
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getResponseBody2(String url){
        // Alternative to OkHttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String html = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(html);
            return html;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Using Gsons JsonObject and JsonArray to represent the JSON structure:
    public JsonObject getWeatherWithJsonObject(String lat, String lon, String key) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}"
                .replace("{lat}", lat)
                .replace("{lon}", lon)
                .replace("{API key}", key);
        String res = getResponseBody(url);
        System.out.println("JSON Structure: " + res);

        JsonElement jsonElement = JsonParser.parseString(res);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject toReturn = new JsonObject();

            // Access specific fields in the JSON
            double temperature = jsonObject
                    .getAsJsonObject("main")
                    .get("temp")
                    .getAsDouble();
            String weatherDescription = jsonObject
                    .getAsJsonArray("weather")
                    .get(0)
                    .getAsJsonObject()
                    .get("description")
                    .getAsString();
            String name = jsonObject
                    .get("name")
                    .getAsString();

            // Build the object to return
            toReturn.addProperty("temperature", temperature);
            toReturn.addProperty("weatherDescription", weatherDescription);
            toReturn.addProperty("name", name);
            return toReturn;
        } else {
            throw new RuntimeException("Not a JSON object");
        }
    }

    public MovieDTO getMovieDetails(String movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=YOUR_API_KEY&language=en-US";
        String res = getResponseBody(url); // Using your existing method
        System.out.println("JSON Structure: " + res);
        MovieDTO movie = gson.fromJson(res, MovieDTO.class);
        return movie;
    }

    public class MovieDTO {
        private String title;
        private String overview;
        private String releaseDate; // For simplicity, we'll keep the release date as a String
    }

}