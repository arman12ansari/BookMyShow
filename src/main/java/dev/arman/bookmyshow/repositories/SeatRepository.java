package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Override
    Seat save(Seat seat);

    @Override
    Optional<Seat> findById(Long seatId);
}
