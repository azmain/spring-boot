package azmain.github.io.repository.mappers;

import azmain.github.io.domain.movie.Movie;
import azmain.github.io.domain.movie.MovieLink;
import azmain.github.io.exception.MyCustomException;
import azmain.github.io.repository.jpa.MovieLinkRepository;
import azmain.github.io.repository.jpa.MovieRepository;
import azmain.github.io.repository.schema.MovieEntity;
import azmain.github.io.repository.schema.MovieLinkEntity;
import azmain.github.io.util.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieLinkMapper {

    private final MovieLinkRepository movieLinkRepository;
    private final MovieRepository movieRepository;

    public MovieLinkMapper(
            MovieLinkRepository movieLinkRepository,
            MovieRepository movieRepository) {
        this.movieLinkRepository = movieLinkRepository;
        this.movieRepository = movieRepository;
    }

    public ResultMapper<MovieLink, MovieLinkEntity> domainToEntity(){
        return domain -> movieLinkRepository.findById(domain.getId())
                .orElseGet(MovieLinkEntity::new)
                .setId(domain.getId())
                .setLinkName(domain.getLinkName())
                .setLinkUrl(domain.getLinkUrl());
    }

    public ResultMapper<MovieLinkEntity, MovieLink> entityToDomain(){
        return entity -> new MovieLink()
                .setId(entity.getId())
                .setLinkName(entity.getLinkName())
                .setLinkUrl(entity.getLinkUrl())
                .setMovieId(entity.getMovie().getId());
    }
}
