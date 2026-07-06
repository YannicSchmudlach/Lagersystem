package com.schmudlach.lagersystem.security;

public record SuccessfulLoginDTO(
        String accessToken,
        String tokenType,
        String expiresAt,
        Role role
) {
}
