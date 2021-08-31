package com.stomatology.dto.create;

import com.stomatology.dto.ScheduleDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CreateDoctorDto extends CreateUserDto implements Serializable {
    private Integer experience;
    private String phoneNumber;
    private Long specialtyId;
    private Set<Long> servicesIds;
    private List<ScheduleDto> schedules;
}
