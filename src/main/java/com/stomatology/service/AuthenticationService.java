package com.stomatology.service;
import com.stomatology.constants.AuthenticationConstants;
import com.stomatology.dto.LoginDto;
import com.stomatology.security.JwtModel;
import com.stomatology.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountService accountService;

    @Value("${app.jwt.refresh-exp}")
    private Long refreshCookieMaxAge;

    public String login(LoginDto credentials, HttpServletResponse httpServletResponse) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String email = authentication.getName();
        JwtModel jwts = jwtService.getTokens(accountService.findByEmail(email));

        Cookie refreshCookie = new Cookie(AuthenticationConstants.REFRESH_COOKIE, jwts.getRefreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setMaxAge(refreshCookieMaxAge.intValue());
        httpServletResponse.addCookie(refreshCookie);

        return jwts.getAccessToken();
    }
}
