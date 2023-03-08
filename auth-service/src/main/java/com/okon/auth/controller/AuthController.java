package com.okon.auth.controller;


import com.okon.auth.model.Credentials;
import com.okon.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final TokenService tokenService;


    @PostMapping
    public Credentials token(Authentication authentication) {
        log.debug(authentication.toString());
        return tokenService.getCredentials(authentication);
    }
}
