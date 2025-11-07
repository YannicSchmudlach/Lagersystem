package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "einkaufsladen",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_einkaufsladen_name",
                        columnNames = {"name"}
                )})
public class Einkaufsladen {

    @Id
    @GeneratedValue
    private int einkaufsladenId;

    @NotBlank
    @Column(nullable = false)
    private String name;

}
