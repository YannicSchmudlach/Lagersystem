package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "rezept")
public class Rezept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rezeptId;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "rezept", cascade = CascadeType.ALL)
    private List<RezeptLebensmittel> rezeptLebensmittel;

    @NotBlank
    @Column(nullable = false)
    private String beschreibung;

    private String bild;

    private int leckerheitsskala;

    private int dauer;

    private int personen;
}
