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
@Table(name = "lebensmittel",uniqueConstraints = @UniqueConstraint(name ="uk_lebensmittel_name",columnNames = {"name"}))
public class Lebensmittel {
    @Id
    @GeneratedValue
    private int lebensmittelID;

    @NotBlank
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kategorieId", nullable = false,
            foreignKey = @ForeignKey(name = "fk_food_category"))
    private Kategorie kategorie;

}
