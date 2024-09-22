package dev.arman.bookmyshow.controllers;

import dev.arman.bookmyshow.dtos.CreateBookingRequestDto;
import dev.arman.bookmyshow.dtos.CreateBookingResponseDto;
import dev.arman.bookmyshow.dtos.ResponseStatus;
import dev.arman.bookmyshow.exceptions.InvalidRequestException;
import dev.arman.bookmyshow.exceptions.InvalidResponseException;
import dev.arman.bookmyshow.models.Booking;
import dev.arman.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

/**
 * @author mdarmanansari
 */
@Controller
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto) {
        CreateBookingResponseDto createBookingResponseDto = new CreateBookingResponseDto();

        if (createBookingRequestDto.getUserId() == null || createBookingRequestDto.getShowId() == null ||
                createBookingRequestDto.getShowSeatIds() == null) {
            throw new InvalidRequestException("Invalid request");
        }
        Booking booking = bookingService.createBooking(createBookingRequestDto.getUserId(),
                createBookingRequestDto.getShowSeatIds(), createBookingRequestDto.getShowId());

        if (booking != null) {
            createBookingResponseDto.setBookingId(booking.getId());
            createBookingResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return createBookingResponseDto;
        }

        throw new InvalidResponseException("Invalid response");
    }
}
