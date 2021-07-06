package com.stomatology.mapper;

import com.stomatology.dto.UserDto;
import com.stomatology.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDto> {
    @Mapping(target = "profilePictureUrl", source = "profilePicture.path")
    @Mapping(target = "email", source = "account.email")
    @Override
    UserDto toDto(User entity);
}
