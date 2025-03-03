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
public class Einkaufsliste {
    @Id
    @GeneratedValue
    private int einkaufslisteId;

    @OneToMany
    private List<EinkaufslistenEintrag> eintraege;
}
