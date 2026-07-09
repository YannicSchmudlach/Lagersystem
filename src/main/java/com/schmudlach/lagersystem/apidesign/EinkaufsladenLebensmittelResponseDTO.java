package com.schmudlach.lagersystem.apidesign;

public record EinkaufsladenLebensmittelResponseDTO(
        int einkaufsladenLebensmittelId,
        LebensmittelResponseDTO lebensmittel,
        EinkaufsladenResponseDTO einkaufsladen,
        double preis
) {
}
