package azmain.github.io.repository.mappers;

import azmain.github.io.domain.movie.Movie;
import azmain.github.io.repository.jpa.MovieRepository;
import azmain.github.io.repository.schema.MovieEntity;
import azmain.github.io.util.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    private final MovieRepository movieRepository;

    public MovieMapper(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ResultMapper<Movie, MovieEntity> domainToEntity(){
        return domain -> movieRepository.findById(domain.getId())
                .orElseGet(MovieEntity::new)
                .setId(domain.getId())
                .setMovieName(domain.getMovieName())
                .setDescription(domain.getDescription())
                .setReleaseDate(domain.getReleaseDate());
    }

    public ResultMapper<MovieEntity, Movie> entityToDomain(){
        return entity -> new Movie()
                .setId(entity.getId())
                .setMovieName(entity.getMovieName())
                .setDescription(entity.getDescription())
                .setReleaseDate(entity.getReleaseDate());
    }
}
