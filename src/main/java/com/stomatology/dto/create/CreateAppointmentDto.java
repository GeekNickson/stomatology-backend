package com.stomatology.dto.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private Long doctorId;
    private Long patientId;
    private Long serviceId;
}
