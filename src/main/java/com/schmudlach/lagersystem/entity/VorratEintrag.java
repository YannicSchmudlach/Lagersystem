package com.schmudlach.lagersystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VorratEintrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Lebensmittel lebensmittel;

    private int menge;

    @OneToOne
    private Vorrat vorrat;
}
