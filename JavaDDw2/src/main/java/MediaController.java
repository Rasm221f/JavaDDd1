import java.util.List;

public interface MediaController<T> {
    List<T> getByRating(double rating);
    List<T> getSortedByReleaseDate();
}