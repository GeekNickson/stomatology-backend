package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private String doctorFirstName;
    private String doctorLastName;
    private String patientFirstName;
    private String patientLastName;
    private String service;
    private LocalDateTime dateTime;
}
