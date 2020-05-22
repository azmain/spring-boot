package azmain.github.io.service.movie;

import azmain.github.io.domain.movie.Movie;
import azmain.github.io.util.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    PagedResult<Movie> getAllMovies(Pageable pageable);
    Movie getMovie(long id);
    Long saveMovie(Movie movie);
    void updateMovie(long id, Movie movie);
    void deleteMovie(long id);
}
