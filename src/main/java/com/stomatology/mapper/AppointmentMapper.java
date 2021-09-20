package com.stomatology.mapper;

import com.stomatology.dto.AppointmentDto;
import com.stomatology.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends EntityMapper<Appointment, AppointmentDto> {
    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Override
    Appointment toEntity(AppointmentDto dto);

    @Mapping(target = "patientLastName", source = "patient.user.lastName")
    @Mapping(target = "patientFirstName", source = "patient.user.firstName")
    @Mapping(target = "doctorLastName", source = "doctor.user.lastName")
    @Mapping(target = "doctorFirstName", source = "doctor.user.firstName")
    @Mapping(target = "service", source = "service.name")
    @Override
    AppointmentDto toDto(Appointment entity);
}
