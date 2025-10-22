package com.schmudlach.lagersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EinkaufsladenLebensmittel {


    @Id
    @GeneratedValue
    private int einkaufsladenLebensmittelId;

    @OneToOne
    private Lebensmittel lebensmittel;

    @OneToOne
    private Einkaufsladen einkaufsladen;

    private double preis;
}
