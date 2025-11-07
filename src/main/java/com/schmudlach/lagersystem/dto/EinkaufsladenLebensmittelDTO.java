package com.schmudlach.lagersystem.dto;

public record EinkaufsladenLebensmittelDTO(
        int lebensmittelId,
        int einkaufsladenId,
        double preis
) {

}
