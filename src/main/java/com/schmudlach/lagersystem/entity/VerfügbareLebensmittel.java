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
public class VerfügbareLebensmittel {

    @Id
    @GeneratedValue
    private int verfuegbareLebensmittelId;

    @OneToOne
    private Lebensmittel lebensmittel;

    private int anzahl;

    private int threshold;

}
