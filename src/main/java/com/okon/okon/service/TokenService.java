package com.okon.okon.service;

import com.okon.okon.converter.ModelConverter;
import com.okon.okon.model.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final UserService userService;
    private final ModelConverter modelConverter;
    private final JwtEncoder encoder;
    private static final long EXPIRE = 2_600_000L;

    public String getToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRE))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

    public Credentials getCredentials(Authentication authentication) {
        return Credentials.builder()
                .token(getToken(authentication))
                .user(modelConverter.userConvertToDTO(userService.findByUsername(authentication.getName())).get())
                .build();
    }
}
