package azmain.github.io.repository.schema;

import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.MOVIE_LINK_TABLE_NAME)
public class MovieLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "LINK_NAME")
    private String linkName;
    @Column(name = "LINK_URL")
    private String linkUrl;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private MovieEntity movie;

    public long getId() {
        return id;
    }

    public MovieLinkEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getLinkName() {
        return linkName;
    }

    public MovieLinkEntity setLinkName(String linkName) {
        this.linkName = linkName;
        return this;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public MovieLinkEntity setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        return this;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public MovieLinkEntity setMovie(MovieEntity movie) {
        this.movie = movie;
        return this;
    }
}
