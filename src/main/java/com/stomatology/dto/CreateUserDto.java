package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String firstName;

    private String lastName;

    private String profilePicture;

    private String email;

    private String password;
}
