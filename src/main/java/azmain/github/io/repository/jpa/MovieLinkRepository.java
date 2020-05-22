package azmain.github.io.repository.jpa;

import azmain.github.io.repository.schema.MovieLinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieLinkRepository extends JpaRepository<MovieLinkEntity, Long> {

    List<MovieLinkEntity> findAllByMovie_Id(long movieId);

}
