package azmain.github.io.controller.movie;

import azmain.github.io.domain.movie.MovieLink;
import azmain.github.io.service.movie.MovieLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "movies/{movieId}/links", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieLinkController {

    @Autowired private MovieLinkService movieLinkService;

    @GetMapping
    public ResponseEntity<?> getMovieLinks(
            @PathVariable("movieId") final long movieId
    ){
        return ResponseEntity.ok(movieLinkService.getMovieLinks(movieId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMovieLinks(
            @PathVariable("movieId") final long movieId,
            @RequestBody List<MovieLink> movieLinks
            ){
        movieLinkService.saveMovieLinks(movieId, movieLinks);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMovieLinks(
            @PathVariable("movieId") final long movieId,
            @RequestBody List<MovieLink> movieLinks
    ){
        movieLinkService.deleteMovieLinks(movieId, movieLinks);
        return ResponseEntity.ok().build();
    }


    @PutMapping(path = "{linkId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMovieLink(
            @PathVariable("movieId") final long movieId,
            @PathVariable("linkId") final long linkId,
            @RequestBody MovieLink movieLink
    ){
        movieLinkService.updateMovieLink(movieId, linkId, movieLink);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
