package dev.arman.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mdarmanansari
 */

@Getter
@Setter
public class SignUpResponseDto {
    private Long Id;
    private ResponseStatus responseStatus;
}
