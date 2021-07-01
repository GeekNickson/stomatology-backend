package com.stomatology.dto.create;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class CreateDoctorDto extends CreateUserDto implements Serializable {
    private Integer experience;
    private String phoneNumber;
    private Long specialtyId;
    private Set<Long> servicesIds;
}
