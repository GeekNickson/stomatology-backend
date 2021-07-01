package com.stomatology.mapper;

import com.stomatology.dto.DoctorDto;
import com.stomatology.entity.user.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SpecialtyMapper.class, ServiceMapper.class})
public interface DoctorMapper extends EntityMapper<Doctor, DoctorDto> {

    @Mapping(target = "profilePictureUrl", source = "user.profilePicture.path")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "email", source = "user.account.email")
    @Override
    DoctorDto toDto(Doctor entity);
}
