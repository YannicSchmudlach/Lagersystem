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
public class Kategorie {

    @Id
    @GeneratedValue
    private int kategorieId;

    private String name;
}
