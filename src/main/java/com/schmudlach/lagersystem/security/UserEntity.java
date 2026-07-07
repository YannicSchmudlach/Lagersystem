package com.schmudlach.lagersystem.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "app_user",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_app_user_username",
                columnNames = {"user_name"}
        ),
        @UniqueConstraint(
                name = "uk_app_user_email",
                columnNames = {"email"}
        )
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String passwordHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
