package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class DoctorDto extends UserDto implements Serializable {
    private String phoneNumber;
    private SpecialtyDto specialty;
    private Set<ServiceDto> services;
    private List<ScheduleDto> scheduleDto;
}
