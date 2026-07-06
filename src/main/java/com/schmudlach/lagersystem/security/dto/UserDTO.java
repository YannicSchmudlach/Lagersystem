package com.schmudlach.lagersystem.security.dto;

public record UserDTO(
        String userName,
        String email,
        String password
) {
}
