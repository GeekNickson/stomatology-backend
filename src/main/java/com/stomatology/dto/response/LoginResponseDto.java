package com.stomatology.dto.response;

import com.stomatology.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginResponseDto {
    private final UserDto user;
    private final String jwt;
}
