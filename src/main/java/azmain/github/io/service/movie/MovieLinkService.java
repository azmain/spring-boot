package azmain.github.io.service.movie;

import azmain.github.io.domain.movie.MovieLink;

import java.util.List;

public interface MovieLinkService {

    List<MovieLink> getMovieLinks(long movieId);

    void saveMovieLinks(long movieId, List<MovieLink> movieLinks);

    void deleteMovieLinks(long movieId, List<MovieLink> movieLinks);

    void updateMovieLink(long movieId, long linkId, MovieLink movieLink);
}
