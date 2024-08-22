package com.schmudlach.lagersystem.entity;

import com.schmudlach.lagersystem.entity.Kategorie;
import jakarta.persistence.*;
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

    @ManyToOne
    private Kategorie kategorie;
}
