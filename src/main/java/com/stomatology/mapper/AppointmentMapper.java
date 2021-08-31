package com.stomatology.mapper;

import com.stomatology.dto.AppointmentDto;
import com.stomatology.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends EntityMapper<Appointment, AppointmentDto> {
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Override
    Appointment toEntity(AppointmentDto dto);

    @Mapping(target = "serviceId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "doctorId", source = "service.id")
    @Override
    AppointmentDto toDto(Appointment entity);
}

