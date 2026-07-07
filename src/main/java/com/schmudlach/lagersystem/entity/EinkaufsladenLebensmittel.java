package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "einkaufsladen_lebensmittel",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_einkaufsladen_lebensmittel_laden_leb",
                        columnNames = {"einkaufsladen_id", "lebensmittel_id"}
                )
        }
)
public class EinkaufsladenLebensmittel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int einkaufsladenLebensmittelId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "lebensmittel_id",                 // FK-Spalte in DIESER Tabelle
            referencedColumnName = "lebensmittelID", // PK-Spalte in Lebensmittel
            nullable = false
    )
    private Lebensmittel lebensmittel;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "einkaufsladen_id",                 // FK-Spalte in DIESER Tabelle
            referencedColumnName = "einkaufsladenId", // PK-Spalte in Einkaufsladen
            nullable = false
    )
    private Einkaufsladen einkaufsladen;

    private double preis;
}
