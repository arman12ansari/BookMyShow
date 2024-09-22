package dev.arman.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mdarmanansari
 */

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
}
