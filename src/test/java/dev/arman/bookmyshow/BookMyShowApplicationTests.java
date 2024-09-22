package dev.arman.bookmyshow;

import dev.arman.bookmyshow.controllers.UserController;
import dev.arman.bookmyshow.dtos.LoginRequestDto;
import dev.arman.bookmyshow.dtos.LoginResponseDto;
import dev.arman.bookmyshow.dtos.SignUpRequestDto;
import dev.arman.bookmyshow.dtos.SignUpResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowApplicationTests {
    @Autowired
    private UserController userController;

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
}
