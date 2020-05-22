package azmain.github.io.repository.jpa;

import azmain.github.io.repository.schema.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
