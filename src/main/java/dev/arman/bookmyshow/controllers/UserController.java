package dev.arman.bookmyshow.controllers;

import dev.arman.bookmyshow.dtos.*;
import dev.arman.bookmyshow.exceptions.InvalidRequestException;
import dev.arman.bookmyshow.exceptions.InvalidResponseException;
import dev.arman.bookmyshow.models.User;
import dev.arman.bookmyshow.services.UserServices;
import org.springframework.stereotype.Controller;

/**
 * @author mdarmanansari
 */

@Controller
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        if (signUpRequestDto.getName() == null || signUpRequestDto.getEmail() == null ||
                signUpRequestDto.getPassword() == null) {
            throw new InvalidRequestException("Name, email and password are required");
        }

        User user = userServices.signUp(signUpRequestDto.getName(),
                signUpRequestDto.getEmail(), signUpRequestDto.getPassword());

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        if (user != null) {
            signUpResponseDto.setId(user.getId());
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return signUpResponseDto;
        }

        throw new InvalidResponseException("User not created");
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null) {
            throw new InvalidRequestException("Email and password are required");
        }

        User user = userServices.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        if (user != null) {
            loginResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return loginResponseDto;
        }

        throw new InvalidResponseException("User not found");
    }
}
