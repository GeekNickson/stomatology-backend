package com.stomatology.resource;

import com.stomatology.dto.LoginDto;
import com.stomatology.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto credentials, HttpServletResponse httpServletResponse) {
        return authenticationService.login(credentials, httpServletResponse);
    }
}
