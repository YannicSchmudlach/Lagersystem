package com.schmudlach.lagersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rezept {
    @Id
    @GeneratedValue
    private int rezeptId;

    @OneToMany
    private List<Lebensmittel> lebensmittelList;

    private String beschreibung;

    private String bild;

    private int leckerheitsskala;

    private int dauer;

    private int personen;
}
