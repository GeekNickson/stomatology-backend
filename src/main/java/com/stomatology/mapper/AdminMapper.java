package com.stomatology.mapper;

import com.stomatology.dto.AdminDto;
import com.stomatology.entity.user.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper extends EntityMapper<Admin, AdminDto> {
    @Mapping(target = "profilePictureUrl", source = "user.profilePicture.path")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "email", source = "user.account.email")
    @Override
    AdminDto toDto(Admin entity);
}
