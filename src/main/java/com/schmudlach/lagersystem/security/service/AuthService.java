package com.schmudlach.lagersystem.security.service;

import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.security.*;
import com.schmudlach.lagersystem.security.dto.LoginDTO;
import com.schmudlach.lagersystem.security.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public boolean passwordMatches(final String rawPassword, final UserEntity user) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }

    public String hashPassword(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    @Transactional
    public UserEntity insertUser(final UserDTO dto) {
        final String normalizedUserName = dto.userName().trim().toLowerCase(Locale.ROOT);
        final String normalizedEmail = dto.email().trim().toLowerCase(Locale.ROOT);

        userRepository.findByUserName(normalizedUserName).ifPresent(tmp -> {
            throw new ConflictException("Benutzername " + tmp.getUserName() + " ist bereits vergeben");
        });

        userRepository.findByEmail(normalizedEmail).ifPresent(tmp -> {
            throw new ConflictException("Email " + tmp.getEmail() + " ist bereits vergeben");
        });

        UserEntity entity = UserEntity.builder().userName(normalizedUserName).email(normalizedEmail).role(Role.USER).passwordHash(hashPassword(dto.password())).build();
        return userRepository.save(entity);
    }
    @Transactional
    public SuccessfulLoginDTO login(final LoginDTO loginDTO){
        final String normalizedEmail = loginDTO.email().trim().toLowerCase(Locale.ROOT);
        UserEntity user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new ResponseStatusException(
                        UNAUTHORIZED,
                        "Ungültige Zugangsdaten"
                ));


        if (!passwordEncoder.matches(loginDTO.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Ungültige Zugangsdaten"
            );
        }

        return jwtService.createToken(user);
    }
}
