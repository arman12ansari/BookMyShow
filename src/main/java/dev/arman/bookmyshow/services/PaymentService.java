package dev.arman.bookmyshow.services;

import dev.arman.bookmyshow.exceptions.BookingNotFoundException;
import dev.arman.bookmyshow.models.*;
import dev.arman.bookmyshow.repositories.BookingRepository;
import dev.arman.bookmyshow.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author mdarmanansari
 */
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public Payment makePayment(String referenceId, int amount) {
        Optional<Booking> optionalBooking = bookingRepository.findByBookingNumber(referenceId);

        if (optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found with referenceId: " + referenceId);
        }

        Booking booking = optionalBooking.get();

        Payment payment = new Payment();

        payment.setReferenceNumber(referenceId);
        payment.setAmount(amount);
        payment.setPaymentProvider(PaymentProvider.RAZORPAY);
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);

        paymentRepository.save(payment);

        booking.setPayments(List.of(payment));
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

        return payment;

    }
}
