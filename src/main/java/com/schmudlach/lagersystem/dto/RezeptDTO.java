package com.schmudlach.lagersystem.dto;

import java.util.List;

public record RezeptDTO(
        List<RezeptLebensmittelDTO> rezeptLebensmittelList,
        String beschreibung,
        String bild,
        int leckerheitsskala,
        int dauer,
        int personen) {
}
