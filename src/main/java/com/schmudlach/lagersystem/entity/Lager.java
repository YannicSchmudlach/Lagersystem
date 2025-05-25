package com.schmudlach.lagersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lager {

    @Id
    @GeneratedValue
    private int lagerID;

    @ManyToOne
    private Lebensmittel lebensmittel;

    private double verfügbareMenge;
}
