package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "verfügbarelebensmittel",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_verfügbarelebensmittel_lebensmittel",
                        columnNames = {"lebensmittel_id"}
                )})
public class VerfügbareLebensmittel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int verfuegbareLebensmittelId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lebensmittel_id",
            nullable = false,
            referencedColumnName = "lebensmittelID",
            foreignKey = @ForeignKey(name = "fk_food_lebensmittel"))

    private Lebensmittel lebensmittel;

    @Column(nullable = false)
    private double anzahl;

    @Column(nullable = false)
    private int threshold;

}
