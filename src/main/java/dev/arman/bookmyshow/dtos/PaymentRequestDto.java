package dev.arman.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mdarmanansari
 */
@Getter
@Setter
public class PaymentRequestDto {
    private String referenceId;
    private int amount;
}
