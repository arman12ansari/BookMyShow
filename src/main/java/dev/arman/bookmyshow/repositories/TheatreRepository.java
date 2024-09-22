package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    @Override
    Theatre save(Theatre theatre);

    @Override
    Optional<Theatre> findById(Long theatreId);
}
