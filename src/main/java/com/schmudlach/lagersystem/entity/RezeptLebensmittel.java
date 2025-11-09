package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "rezeptlebensmittel",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_rezeptlebensmittel_rezept_lebensmittel",
                        columnNames = {"rezept_id", "lebensmittel_id"}
                )})
public class RezeptLebensmittel {
    @Id
    @GeneratedValue
    private int rezeptLebensmittelId;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lebensmittel_id",
            referencedColumnName = "lebensmittelID",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_rezeptlebensmittel_lebensmittel"))
    private Lebensmittel lebensmittel;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rezept_Id",
            nullable = false,
            referencedColumnName = "rezeptId",
            foreignKey = @ForeignKey(name = "fk_rezeptlebensmittel_rezept"))
    private Rezept rezept;

    @NotBlank
    @Column(nullable = false)
    private double menge;

    @NotBlank
    @Column(nullable = false)
    private String einheit;
}
