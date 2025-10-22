package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;

public record EinkaufsladenLebensmittelDTO(
        LebensmittelDTO lebensmittel,
        EinkaufsladenDTO einkaufsladen,
        double preis
) {
    public EinkaufsladenLebensmittel toEinkaufsladenLebensmittel() throws Exception {
        if (lebensmittel == null) {
            throw new Exception();
        }

        if (einkaufsladen == null) {
            throw new Exception();
        }
        return EinkaufsladenLebensmittel.builder().lebensmittel(lebensmittel.toLebensmittel()).einkaufsladen(einkaufsladen.toEinkaufsladen()).preis(preis).build();
    }
}
