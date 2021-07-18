package com.stomatology.resource;

import com.stomatology.dto.LoginDto;
import com.stomatology.dto.UserDto;
import com.stomatology.dto.create.CreatePatientDto;
import com.stomatology.dto.response.LoginResponseDto;
import com.stomatology.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto credentials, HttpServletResponse httpServletResponse) {
        return authenticationService.login(credentials, httpServletResponse);
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponseDto register(@ModelAttribute CreatePatientDto patientDto, HttpServletResponse httpServletResponse) {
        return authenticationService.register(patientDto, httpServletResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        authenticationService.logout(httpServletRequest, httpServletResponse);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/refresh")
    public String refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return authenticationService.refresh(httpServletRequest, httpServletResponse);
    }

    @GetMapping("/auth")
    public UserDto getAuth() {
        return authenticationService.getAuth();
    }
}
