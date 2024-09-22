package dev.arman.bookmyshow.services;

import dev.arman.bookmyshow.models.Show;
import dev.arman.bookmyshow.models.ShowSeat;
import dev.arman.bookmyshow.models.ShowSeatType;
import dev.arman.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mdarmanansari
 */

@Service
public class PriceCalculator {
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats) {
        int amount = 0;

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        if (showSeatTypes != null) {
            for (ShowSeat showSeat : showSeats) {
                for (ShowSeatType showSeatType : showSeatTypes) {
                    if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                        amount += showSeatType.getPrice();
                        break;
                    }
                }
            }
        }

        return amount;
    }
}
