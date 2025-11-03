package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;

public record LebensmittelDTO(
        String name,
        String einheit,
        Kategorie kategorie,
        int threshold
) {
    public Lebensmittel toLebensmittel(){
        return Lebensmittel.builder().name(name).kategorie(kategorie).build();
    }
}
