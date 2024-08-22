package com.schmudlach.lagersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lebensmittel {
    @Id
    @GeneratedValue
    private int lebensmittelID;

    private String name;

    private Double preis;

    private String einkaufsLaden;

    private String einheit;
}
