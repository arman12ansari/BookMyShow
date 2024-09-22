package dev.arman.bookmyshow;

import dev.arman.bookmyshow.controllers.BookingController;
import dev.arman.bookmyshow.controllers.UserController;
import dev.arman.bookmyshow.dtos.*;
import dev.arman.bookmyshow.models.*;
import dev.arman.bookmyshow.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookMyShowApplicationTests {
    @Autowired
    private UserController userController;

    @Autowired
    private BookingController bookingController;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSignUp() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("Arman Ansari");
        signUpRequestDto.setEmail("arman12ansari@gmail.com");
        signUpRequestDto.setPassword("Arman@123");

        SignUpResponseDto signUpResponseDto = userController.signUp(signUpRequestDto);

        System.out.println(signUpResponseDto.getId());
        System.out.println(signUpResponseDto.getResponseStatus());
    }

    @Test
    public void testLogin() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("arman12ansari@gmail.com");
        loginRequestDto.setPassword("Arman@123");

        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);

        System.out.println(loginResponseDto.getResponseStatus());
    }

    @Test
    public void testBooking() {
        CreateBookingRequestDto createBookingRequestDto = new CreateBookingRequestDto();
        createBookingRequestDto.setUserId(1L);
        createBookingRequestDto.setShowId(1L);
        createBookingRequestDto.setShowSeatIds(List.of(1L));

        CreateBookingResponseDto createBookingResponseDto =
                bookingController.createBooking(createBookingRequestDto);

        System.out.println(createBookingResponseDto.getBookingId());
        System.out.println(createBookingResponseDto.getResponseStatus());
    }

    @Test
    public void testInsertSeat() {
        Seat seat1 = new Seat();
        seat1.setRowNum(1);
        seat1.setColNum(1);
        seat1.setNumber("A1");
        seat1.setSeatType(SeatType.GOLD);

        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setRowNum(1);
        seat2.setColNum(2);
        seat2.setNumber("A2");
        seat2.setSeatType(SeatType.GOLD);

        seatRepository.save(seat2);
    }

    @Test
    public void testInsertScreen() {
        Optional<Seat> seat1 = seatRepository.findById(1L);
        Optional<Seat> seat2 = seatRepository.findById(2L);

        Screen screen = new Screen();
        screen.setName("Screen 1");

        screen.setSeats(new ArrayList<>());

        seat1.ifPresent(seat -> screen.getSeats().add(seat));
        seat2.ifPresent(seat -> screen.getSeats().add(seat));

        screen.setFeatures(List.of(Feature.IMAX, Feature.THREE_D));

        screenRepository.save(screen);
    }

    @Test
    public void testInsertRegion() {
        Region region = new Region();

        region.setName("Bengaluru");
        regionRepository.save(region);
    }

    @Test
    public void testInsertTheatre() {
        Optional<Region> region = regionRepository.findById(1L);
        Optional<Screen> screen = screenRepository.findById(1L);

        Theatre theatre = new Theatre();
        theatre.setScreens(new ArrayList<>());
        theatre.setName("PVR Vega City");

        region.ifPresent(theatre::setRegion);

        screen.ifPresent(value -> theatre.getScreens().add(value));

        theatreRepository.save(theatre);
    }

    @Test
    public void testInsertMovie() {
        Movie movie = new Movie();
        movie.setName("The Dark Knight");
        movie.setReleaseDate(new Date());
        movie.setFeatures(List.of(Feature.IMAX, Feature.THREE_D));

        movieRepository.save(movie);
    }

    @Test
    public void testInsertShow() {
        Optional<Movie> movie = movieRepository.findById(1L);
        Optional<Screen> screen = screenRepository.findById(1L);

        Show show = new Show();
        show.setFeatures(List.of(Feature.IMAX, Feature.THREE_D));
        show.setStartTime(new Date());
        show.setEndTime(new Date());

        movie.ifPresent(show::setMovie);
        screen.ifPresent(show::setScreen);

        showRepository.save(show);

    }

    @Test
    public void testInsertShowSeat() {
        Optional<Show> show = showRepository.findById(1L);
        Optional<Seat> seat = seatRepository.findById(1L);

        ShowSeat showSeat = new ShowSeat();

        show.ifPresent(showSeat::setShow);
        seat.ifPresent(showSeat::setSeat);

        showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);

        showSeatRepository.save(showSeat);
    }

    @Test
    public void testInsertShowSeatType() {
        Optional<Show> show = showRepository.findById(1L);

        ShowSeatType showSeatType1 = new ShowSeatType();
        showSeatType1.setPrice(1000);
        showSeatType1.setSeatType(SeatType.GOLD);
        show.ifPresent(showSeatType1::setShow);

        ShowSeatType showSeatType2 = new ShowSeatType();
        showSeatType2.setPrice(2000);
        showSeatType2.setSeatType(SeatType.PLATINUM);
        show.ifPresent(showSeatType2::setShow);

        showSeatTypeRepository.save(showSeatType1);
        showSeatTypeRepository.save(showSeatType2);

    }
}
