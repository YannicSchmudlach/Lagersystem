package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Rezept;

import java.util.List;

public record RezeptDTO(
        List<LebensmittelDTO> lebensmittelList,
        String beschreibung,
        String bild,
        int leckerheitsskala,
        int dauer,
        int personen) {

    public Rezept toRezept() {
        //TODO .lebensmittelList(lebensmittelList.stream()
        //                        .map(LebensmittelDTO::toLebensmittel).toList())
        return Rezept.builder()
                .beschreibung(beschreibung)
                .bild(bild)
                .leckerheitsskala(leckerheitsskala)
                .dauer(dauer)
                .personen(personen)
                .build();
    }
}
