package azmain.github.io.service.movie;

import azmain.github.io.domain.movie.Movie;
import azmain.github.io.exception.MyCustomException;
import azmain.github.io.repository.jpa.MovieRepository;
import azmain.github.io.repository.mappers.MovieMapper;
import azmain.github.io.repository.schema.MovieEntity;
import azmain.github.io.util.ListResultBuilder;
import azmain.github.io.util.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAll();

        return ListResultBuilder.listBuild(movieEntities, movieMapper.entityToDomain());

    }

    @Override
    public PagedResult<Movie> getAllMovies(Pageable pageable) {
        Page<MovieEntity> movieEntities = movieRepository.findAll(pageable);

        return ListResultBuilder.pageListBuild(movieEntities, movieMapper.entityToDomain());
    }

    @Override
    public Movie getMovie(long id) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(()-> new MyCustomException("Movie not found."));

        return movieMapper.entityToDomain().map(movieEntity);
    }

    @Override
    @Transactional
    public Long saveMovie(Movie movie) {
        MovieEntity movieEntity = movieMapper.domainToEntity().map(movie);
        return movieRepository.save(movieEntity).getId();
    }

    @Override
    @Transactional
    public void updateMovie(long id, Movie movie) {
        MovieEntity movieEntity = movieRepository.findById(id)
                .orElseThrow(()-> new MyCustomException("Movie not found."));
        MovieEntity updatedMovieEntity = movieMapper.domainToEntity().map(movie);
        movieRepository.save(updatedMovieEntity);
    }

    @Override
    @Transactional
    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}
