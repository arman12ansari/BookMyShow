package dev.arman.bookmyshow.repositories;

import dev.arman.bookmyshow.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mdarmanansari
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Override
    Payment save(Payment payment);
}
