package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private Long doctorId;
    private Long patientId;
    private Long serviceId;
}
