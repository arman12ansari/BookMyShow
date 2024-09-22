package dev.arman.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author mdarmanansari
 */

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> showSeatIds;
}
