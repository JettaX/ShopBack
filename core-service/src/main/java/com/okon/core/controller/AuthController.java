package com.okon.core.controller;


import com.okon.core.model.Credentials;
import com.okon.core.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {
    private final TokenService tokenService;


    @PostMapping
    public Credentials token(Authentication authentication) {
        log.info(authentication.toString());
        return tokenService.getCredentials(authentication);
    }

    @GetMapping("/health")
    public boolean getHealth() {
        return true;
    }
}
