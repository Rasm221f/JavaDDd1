import lombok.*;

@Getter
@ToString
public class MovieDTO {
    private String title;
    private String overview;
    private String releaseDate;
    private double rating;
}