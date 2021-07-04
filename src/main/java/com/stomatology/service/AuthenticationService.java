package com.stomatology.service;

import com.stomatology.constants.AuthenticationConstants;
import com.stomatology.dto.LoginDto;
import com.stomatology.dto.create.CreatePatientDto;
import com.stomatology.entity.user.Account;
import com.stomatology.entity.user.RefreshToken;
import com.stomatology.security.JwtModel;
import com.stomatology.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountService accountService;
    private final PatientService patientService;
    private final RefreshTokenService refreshTokenService;

    @Value("${app.jwt.refresh-exp}")
    private Long refreshCookieMaxAge;

    public String login(LoginDto credentials, HttpServletResponse httpServletResponse) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String email = authentication.getName();
        Account account = accountService.findByEmail(email);

        JwtModel jwts = jwtService.getTokens(account);
        refreshTokenService.saveRefreshToken(jwts.getRefreshToken(), account);

        setRefreshCookie(jwts.getRefreshToken(), httpServletResponse);
        return jwts.getAccessToken();
    }

    public String register(CreatePatientDto patientDto, HttpServletResponse httpServletResponse) {
        patientService.create(patientDto);
        return login(new LoginDto(patientDto.getEmail(), patientDto.getPassword()), httpServletResponse);
    }

    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Optional<Cookie> maybeRefreshCookie = Optional
                .ofNullable(WebUtils.getCookie(httpServletRequest, AuthenticationConstants.REFRESH_COOKIE));

        maybeRefreshCookie.ifPresent(cookie -> {
            refreshTokenService.deleteRefreshToken(cookie.getValue(),
                    accountService.findByEmail(jwtService.getSubject(cookie.getValue())));
            removeCookie(cookie, httpServletResponse);
        });
    }

    public String refresh(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie refreshCookie = Optional
                .ofNullable(WebUtils.getCookie(httpServletRequest, AuthenticationConstants.REFRESH_COOKIE))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No token provided"));

        String token = refreshCookie.getValue();
        if (!jwtService.validate(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        Account account = accountService.findByEmail(jwtService.getSubject(token));
        Set<RefreshToken> refreshTokens = refreshTokenService.findByAccount(account);

        RefreshToken refreshToken = refreshTokens.stream()
                .filter(existingToken -> existingToken.getRefreshToken().equals(token))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid token"));

        JwtModel jwts = jwtService.getTokens(account);
        refreshToken.setRefreshToken(jwts.getRefreshToken());
        refreshToken.setAccount(account);
        refreshTokenService.saveRefreshToken(refreshToken);

        setRefreshCookie(jwts.getRefreshToken(), httpServletResponse);
        return jwts.getAccessToken();

    }

    private void removeCookie(Cookie cookie, HttpServletResponse httpServletResponse) {
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    private void setRefreshCookie(String refreshToken, HttpServletResponse httpServletResponse) {
        Cookie refreshCookie = new Cookie(AuthenticationConstants.REFRESH_COOKIE, refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setMaxAge(refreshCookieMaxAge.intValue());
        httpServletResponse.addCookie(refreshCookie);
    }
}
