package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;

public record EinkaufsladenLebensmittelDTO(
        int lebensmittelId,
        int einkaufsladenId,
        double preis
) {

}
