package com.stomatology.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtModel {
    private final String accessToken;
    private final String refreshToken;
}
