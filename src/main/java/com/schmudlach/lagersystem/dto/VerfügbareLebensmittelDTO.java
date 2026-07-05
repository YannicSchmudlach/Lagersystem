package com.schmudlach.lagersystem.dto;

public record VerfügbareLebensmittelDTO(
        int lebensmittelId,
        double anzahl,
        int threshold
) {
}
