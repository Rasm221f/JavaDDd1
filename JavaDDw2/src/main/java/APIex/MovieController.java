package APIex;

import java.util.List;
public class MovieController {
    public List<MovieDTO> getByRating(double rating) {

        return fetchMoviesByRating(rating);
    }

    public List<MovieDTO> getSortedByReleaseDate() {
        List<MovieDTO> movies = fetchAllMovies();
        movies.sort((movie1, movie2) -> movie2.getReleaseDate().compareTo(movie1.getReleaseDate()));
        return movies;
    }

    private List<MovieDTO> fetchMoviesByRating(double rating) {
        return null;
    }

    private List<MovieDTO> fetchAllMovies() {
        return null;
    }
}

