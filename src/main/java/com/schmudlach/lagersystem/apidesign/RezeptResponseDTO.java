package com.schmudlach.lagersystem.apidesign;

import com.schmudlach.lagersystem.entity.RezeptLebensmittel;

import java.util.List;

public record RezeptResponseDTO(
        int rezeptId,
        List<RezeptLebensmittelResponseDTO> rezeptLebensmittel,
        String beschreibung,
        String bild,
        int leckerheitsskala,
        int dauer,
        int personen
) {
}
