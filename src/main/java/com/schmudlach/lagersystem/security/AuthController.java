package com.schmudlach.lagersystem.security;

import com.schmudlach.lagersystem.security.dto.LoginDTO;
import com.schmudlach.lagersystem.security.dto.UserDTO;
import com.schmudlach.lagersystem.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody final UserDTO dto){
        service.insertUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    ResponseEntity<SuccessfulLoginDTO> login(@Valid @RequestBody final LoginDTO dto) {
        SuccessfulLoginDTO response = service.login(dto);
        return ResponseEntity.ok(response);
    }
}
