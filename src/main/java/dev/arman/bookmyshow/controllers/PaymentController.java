package dev.arman.bookmyshow.controllers;

import dev.arman.bookmyshow.dtos.PaymentRequestDto;
import dev.arman.bookmyshow.dtos.PaymentResponseDto;
import dev.arman.bookmyshow.dtos.ResponseStatus;
import dev.arman.bookmyshow.exceptions.InvalidRequestException;
import dev.arman.bookmyshow.exceptions.InvalidResponseException;
import dev.arman.bookmyshow.models.Payment;
import dev.arman.bookmyshow.services.PaymentService;
import org.springframework.stereotype.Controller;

/**
 * @author mdarmanansari
 */

@Controller
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResponseDto makePayment(PaymentRequestDto paymentRequestDto) {
        if (paymentRequestDto.getReferenceId() == null || paymentRequestDto.getAmount() == 0) {
            throw new InvalidRequestException("ReferenceId and amount are required");
        }

        Payment payment = paymentService.makePayment(paymentRequestDto.getReferenceId(),
                paymentRequestDto.getAmount());

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        if (payment != null) {
            paymentResponseDto.setPaymentId(payment.getId());
            paymentResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return paymentResponseDto;
        }

        throw new InvalidResponseException("Payment not created");
    }
}
