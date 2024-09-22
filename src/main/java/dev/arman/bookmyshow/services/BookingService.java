package dev.arman.bookmyshow.services;

import dev.arman.bookmyshow.exceptions.ShowNotFoundException;
import dev.arman.bookmyshow.exceptions.ShowSeatNotFoundException;
import dev.arman.bookmyshow.exceptions.UserNotFoundException;
import dev.arman.bookmyshow.models.*;
import dev.arman.bookmyshow.repositories.BookingRepository;
import dev.arman.bookmyshow.repositories.ShowRepository;
import dev.arman.bookmyshow.repositories.ShowSeatRepository;
import dev.arman.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author mdarmanansari
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final PriceCalculator priceCalculator;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository, PriceCalculator priceCalculator) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculator = priceCalculator;
    }

    public Booking createBooking(Long userId, List<Long> seatIds, Long showId) {
        //1. Get the user with the given userId.
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        User user = optionalUser.get();

        //2. Get the show with the given showId.
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show not found with id: " + showId);
        }

        Show show = optionalShow.get();

        //3. Get the List of showSeats with the given id's.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        //4. Check if all the seats are available or not.
        //5. If not, throw an exception.
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("Seat with id: " + showSeat.getId() + " is not available.");
            }
        }

        //6. If yes, Mark the status of all the seats as BLOCKED.
        checkShowSeat(showSeats);

        //7. Create the booking with pending status. [save booking obj to DB.]
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setUser(user);
        booking.setShow(show);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(showSeats);
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setAmount(priceCalculator.calculatePrice(show, showSeats));

        return bookingRepository.save(booking);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkShowSeat(List<ShowSeat> showSeats) {
        //After taking Lock, check again if showSeats are available or not
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("Seat with id: " + showSeat.getId() + " is not available.");
            }
        }

        //If yes, Mark the status of all the seats as BLOCKED.
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //Save the changes in the DB as well.
            showSeatRepository.save(showSeat);
        }
    }
}
