package com.stomatology.dto.create;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public abstract class CreateUserDto {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected MultipartFile profilePicture;
}
