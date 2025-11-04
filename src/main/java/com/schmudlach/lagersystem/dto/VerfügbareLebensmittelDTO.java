package com.schmudlach.lagersystem.dto;

public record VerfügbareLebensmittelDTO(
        LebensmittelDTO lebensmittel,
        int anzahl,
        int threshold
) {
}
