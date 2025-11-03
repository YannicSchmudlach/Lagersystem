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
@Table(name = "kategorie",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_kategorie_name",
                        columnNames = {"name"}
                )})
public class Kategorie {

    @Id
    @GeneratedValue
    private int kategorieId;

    @NotBlank
    @Column(nullable = false)
    private String name;
}
