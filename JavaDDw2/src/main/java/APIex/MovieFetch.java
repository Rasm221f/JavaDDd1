package APIex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MovieFetch {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        MovieFetch mf = new MovieFetch();
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDE4NWIyMWNjNjczYmU1YjM0NTNlNGQwODJhMDhiYiIsInN1YiI6IjY1YzBjMThhMTJjNjA0MDE2MjAxYzU5MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gOLKfwC0FWTPQhfPf4UrHzvwRoI0eU2875o9T9jLX0E";
        MovieDTO movie = mf.getMovieDetails("550", apiKey);
        System.out.println(movie);
    }

    public MovieDTO getMovieDetails(String movieId, String apiKey) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        String res = getResponseBody(url);
        if (res != null && !res.isEmpty()) {
            return gson.fromJson(res, MovieDTO.class);
        }
        return null;
    }

    private String getResponseBody(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
