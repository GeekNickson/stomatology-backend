package com.stomatology.security;

import com.stomatology.constants.AuthenticationConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AccountDetailsService accountDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(httpServletRequest.getHeader(AuthenticationConstants.AUTHORIZATION_HEADER));
        if (jwtService.validate(token)) {
            String email = jwtService.getSubject(token);
            Authentication authentication = accountDetailsService.getAuthentication(email, token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String resolveToken(String header) {
        if (StringUtils.hasText(header) && header.startsWith(AuthenticationConstants.TOKEN_START)) {
            return header.split(" ")[1].trim();
        }
        return null;
    }
}
