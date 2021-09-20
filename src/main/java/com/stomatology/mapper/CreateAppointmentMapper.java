package com.stomatology.mapper;

import com.stomatology.dto.create.CreateAppointmentDto;
import com.stomatology.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateAppointmentMapper extends EntityMapper<Appointment, CreateAppointmentDto> {
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Override
    Appointment toEntity(CreateAppointmentDto dto);

    @Mapping(target = "serviceId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "doctorId", source = "service.id")
    @Override
    CreateAppointmentDto toDto(Appointment entity);
}

