package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.Data;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;

public record LebensmittelDTO(
        Double preis,
        String name,
        String einkaufsLaden,
        String einheit,
        Kategorie kategorie
) {
    public Lebensmittel toLebensmittel(){
        return Lebensmittel.builder().name(name).einheit(einheit).einkaufsLaden(einkaufsLaden).preis(preis).kategorie(kategorie).build();
    }
}
