package dev.arman.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mdarmanansari
 */
@Getter
@Setter
public class PaymentResponseDto {
    private long paymentId;
    private ResponseStatus responseStatus;
}
