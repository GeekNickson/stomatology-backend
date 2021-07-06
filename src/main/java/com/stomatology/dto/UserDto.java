package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    protected Long id;

    protected String firstName;

    protected String lastName;

    protected String email;

    protected String profilePictureUrl;
}
