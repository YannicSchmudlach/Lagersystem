package com.schmudlach.lagersystem.dto;

public record RezeptLebensmittelDTO(
        int lebensmittelId,
        double menge,
        String einheit
) {
}
