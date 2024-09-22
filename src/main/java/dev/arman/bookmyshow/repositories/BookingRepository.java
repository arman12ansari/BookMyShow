package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mdarmanansari
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    Booking save(Booking booking);

    Optional<Booking> findByBookingNumber(String bookingNumber);
}
