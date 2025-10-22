package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;

public record VerfügbareLebensmittelDTO(
        LebensmittelDTO lebensmittel,
        int anzahl,
        int threshold
) {

    public VerfügbareLebensmittel toVerfügbareLebensmittel() {
        return VerfügbareLebensmittel.builder().anzahl(anzahl).threshold(threshold).lebensmittel(lebensmittel.toLebensmittel()).build();
    }
}
