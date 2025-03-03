package com.schmudlach.lagersystem.entity;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vorrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vorratId;
    private int menge;

    @OneToMany
    private List<VorratEintrag> eintraege;
}
