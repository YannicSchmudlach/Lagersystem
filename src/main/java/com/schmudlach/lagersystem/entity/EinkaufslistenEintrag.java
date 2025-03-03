package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EinkaufslistenEintrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int menge;

    @ManyToOne
    private Lebensmittel lebensmittel;

    @ManyToOne
    private Einkaufsliste einkaufsliste;
}

