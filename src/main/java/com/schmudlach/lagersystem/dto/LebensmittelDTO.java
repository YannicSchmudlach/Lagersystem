package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.Data;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;

import java.util.Optional;

public record LebensmittelDTO(
        Double preis,
        String name,
        String einkaufsLaden,
        String einheit,
        int threshold,
        KategorieDTO kategorie
) {

//    public LebensmittelDTO(Lebensmittel lebensmittel) {
//        this(
//                lebensmittel.getPreis(),
//                lebensmittel.getName(),
//                lebensmittel.getEinkaufsLaden(),
//                lebensmittel.getEinheit(),
//                lebensmittel.getThreshold(),
//                new KategorieDTO(lebensmittel.getKategorie().getName())
//        );
//    }

    public Lebensmittel toLebensmittel() {
        return Lebensmittel.builder().name(name).einheit(einheit).einkaufsLaden(einkaufsLaden).preis(preis).threshold(threshold).kategorie(kategorie.toKategorie()).build();
    }

}
