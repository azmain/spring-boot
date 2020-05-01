package azmain.github.io.repository.schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = SchemaConstant.MOVIE_TABLE_NAME)
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "REALEASE_DATE")
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<MovieLinkEntity> links;

    public long getId() {
        return id;
    }

    public MovieEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getMovieName() {
        return movieName;
    }

    public MovieEntity setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public List<MovieLinkEntity> getLinks() {
        return links;
    }

    public MovieEntity setLinks(List<MovieLinkEntity> links) {
        this.links = links;
        return this;
    }
}
