package com.schmudlach.lagersystem.dto;

public record LebensmittelDTO(
        String name,
        String einheit,
        int kategorieId,
        int threshold
) {
}
