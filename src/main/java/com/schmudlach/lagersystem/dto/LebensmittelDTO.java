package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;

public record LebensmittelDTO(
        String name,
        String einheit,
        int kategorieId,
        int threshold
) {
}
