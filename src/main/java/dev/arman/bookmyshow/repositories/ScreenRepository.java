package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Override
    Screen save(Screen screen);

    @Override
    Optional<Screen> findById(Long screenId);
}
