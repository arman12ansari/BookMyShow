package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Show;
import dev.arman.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author mdarmanansari
 */
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);

    @Override
    ShowSeatType save(ShowSeatType showSeatType);
}
