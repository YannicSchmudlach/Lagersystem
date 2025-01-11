package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
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
