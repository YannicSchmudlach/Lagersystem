package com.schmudlach.lagersystem.dto;

public record VerfügbareLebensmittelDTO(
        int lebensmittelId,
        int anzahl,
        int threshold
) {
}
