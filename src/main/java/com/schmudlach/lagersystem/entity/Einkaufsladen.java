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
public class Einkaufsladen {

    @Id
    @GeneratedValue
    private int einkaufsladenId;

    private String name;

}
