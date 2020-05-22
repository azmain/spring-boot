package azmain.github.io.service.movie;

import azmain.github.io.domain.movie.MovieLink;
import azmain.github.io.exception.MyCustomException;
import azmain.github.io.repository.jpa.MovieLinkRepository;
import azmain.github.io.repository.jpa.MovieRepository;
import azmain.github.io.repository.mappers.MovieLinkMapper;
import azmain.github.io.repository.schema.MovieEntity;
import azmain.github.io.repository.schema.MovieLinkEntity;
import azmain.github.io.util.ListResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieLinkServiceImpl implements MovieLinkService {

    @Autowired
    private MovieLinkRepository movieLinkRepository;
    @Autowired private MovieRepository movieRepository;
    @Autowired
    private MovieLinkMapper movieLinkMapper;

    public List<MovieLink> getMovieLinks(long movieId){
        return ListResultBuilder.listBuild(
                movieLinkRepository.findAllByMovie_Id(movieId),
                movieLinkMapper.entityToDomain());
    }

    @Transactional
    public void saveMovieLinks(long movieId, List<MovieLink> movieLinks){
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(()->new MyCustomException("Movie is not found."));

        List<MovieLinkEntity> movieLinkEntityList =
                ListResultBuilder.listBuild(
                    movieLinks,
                    movieLinkMapper.domainToEntity()
                )
                        .stream()
                        .map(l->l.setMovie(movie))
                        .collect(Collectors.toList());
        movieLinkRepository.saveAll(movieLinkEntityList);
    }

    @Transactional
    public void deleteMovieLinks(long movieId, List<MovieLink> movieLinks){
        List<MovieLinkEntity> movieLinkEntityList =
                ListResultBuilder.listBuild(
                        movieLinks,
                        movieLinkMapper.domainToEntity());
        movieLinkRepository.deleteAll(movieLinkEntityList);
    }


    @Transactional
    public void updateMovieLink(long movieId, long linkId, MovieLink movieLink){
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(()->new MyCustomException("Movie is not found."));

        MovieLinkEntity movieLinkEntity = movieLinkRepository
                .findById(linkId)
                .map(link->{
                    MovieLinkEntity entity = movieLinkMapper.domainToEntity().map(movieLink);
                    entity.setMovie(movie);
                    return entity;
                })
                .orElseThrow(()->new MyCustomException("Movie Link is not found."));
        movieLinkRepository.save(movieLinkEntity);
    }
}
