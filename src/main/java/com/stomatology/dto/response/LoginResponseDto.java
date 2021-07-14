package com.stomatology.dto.response;

import com.stomatology.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginResponseDto {
    private final UserDto user;
    private final String jwt;
}
