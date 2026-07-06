package com.schmudlach.lagersystem.security.service;

import com.schmudlach.lagersystem.security.SuccessfulLoginDTO;
import com.schmudlach.lagersystem.security.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;

    @Value("${security.jwt.expiration-minutes}")
    private long expirationMinutes;

    public SuccessfulLoginDTO createToken(final UserEntity user) {
        final Instant now = Instant.now();
        final Instant expiresAt = now.plus(Duration.ofMinutes(expirationMinutes));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("lagersystem")
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(user.getEmail())
                .claim("userId", user.getUserId())
                .claim("userName", user.getUserName())
                .claim("role", user.getRole().name())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();

        return new SuccessfulLoginDTO(
                token,
                "Bearer",
                expiresAt.toString(),
                user.getRole()
        );
    }
}
