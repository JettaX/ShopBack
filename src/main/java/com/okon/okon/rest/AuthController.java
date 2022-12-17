package com.okon.okon.rest;

import com.okon.okon.converter.ModelConverter;
import com.okon.okon.model.Credentials;
import com.okon.okon.repository.UserRepository;
import com.okon.okon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtEncoder encoder;
    private final UserService userService;
    private final ModelConverter modelConverter;

    @PostMapping
    public Credentials token(Authentication authentication) {
        log.info(authentication.toString());
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return Credentials.builder()
                .token(this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                .user(modelConverter.userConvertToDTO(userRepository.findByUsername(authentication.getName())).get())
                .build();
    }

    @GetMapping("/health")
    public boolean getHealth() {
        return true;
    }
}
