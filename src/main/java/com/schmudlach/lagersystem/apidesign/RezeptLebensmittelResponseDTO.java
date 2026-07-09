package com.schmudlach.lagersystem.apidesign;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;

public record RezeptLebensmittelResponseDTO(
        int rezeptLebensmittelId,
        LebensmittelResponseDTO lebensmittel,
        double menge,
        String einheit
) {
}
