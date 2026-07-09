package com.schmudlach.lagersystem.apidesign;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;

public record LebensmittelResponseDTO(
        int lebensmittelID,
        String name,
        KategorieResponseDTO kategorie
) {

}
