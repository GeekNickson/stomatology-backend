package com.stomatology.mapper;

import com.stomatology.dto.PatientDto;
import com.stomatology.entity.user.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<Patient, PatientDto> {

    @Mapping(target = "roleName", source = "user.account.role.name")
    @Mapping(target = "profilePictureUrl", source = "user.profilePicture.path")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "email", source = "user.account.email")
    @Override
    PatientDto toDto(Patient entity);
}
