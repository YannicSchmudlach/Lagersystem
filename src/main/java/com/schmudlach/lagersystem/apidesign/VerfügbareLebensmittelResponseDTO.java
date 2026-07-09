package com.schmudlach.lagersystem.apidesign;

import com.schmudlach.lagersystem.entity.Lebensmittel;

public record VerfügbareLebensmittelResponseDTO(
        int verfuegbareLebensmittelId,
        LebensmittelResponseDTO lebensmittel,
        double anzahl,
        int threshold
) {
}
