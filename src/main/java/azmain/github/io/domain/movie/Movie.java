package azmain.github.io.domain.movie;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class Movie {

    private long id;

    @NotNull private String movieName;

    private String description;

    private LocalDate releaseDate;

    public long getId() {
        return id;
    }

    public Movie setId(long id) {
        this.id = id;
        return this;
    }

    public String getMovieName() {
        return movieName;
    }

    public Movie setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Movie setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

}
