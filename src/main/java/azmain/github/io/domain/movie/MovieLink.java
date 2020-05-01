package azmain.github.io.domain.movie;

import javax.validation.constraints.NotNull;

public class MovieLink {

    private long id;
    @NotNull private String linkName;
    @NotNull private String linkUrl;
    @NotNull private long movieId;

    public long getId() {
        return id;
    }

    public MovieLink setId(long id) {
        this.id = id;
        return this;
    }

    public String getLinkName() {
        return linkName;
    }

    public MovieLink setLinkName(String linkName) {
        this.linkName = linkName;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public MovieLink setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public long getMovieId() {
        return movieId;
    }

    public MovieLink setMovieId(long movieId) {
        this.movieId = movieId;
        return this;
    }
}
