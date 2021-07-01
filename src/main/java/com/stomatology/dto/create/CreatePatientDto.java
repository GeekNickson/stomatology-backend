package com.stomatology.dto.create;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreatePatientDto extends CreateUserDto implements Serializable {
    private String phoneNumber;
}
