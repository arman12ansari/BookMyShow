package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    Movie save(Movie movie);

    @Override
    Optional<Movie> findById(Long movieId);
}
