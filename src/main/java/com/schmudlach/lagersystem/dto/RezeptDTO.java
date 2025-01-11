package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record RezeptDTO(
        List<LebensmittelDTO> lebensmittelList,
        String beschreibung,
        String bild,
        int leckerheitsskala,
        int dauer,
        int personen

) {
    public Rezept toRezept(){
        return Rezept.builder().lebensmittelList(lebensmittelList.stream()
                .map(LebensmittelDTO::toLebensmittel).toList())
                .beschreibung(beschreibung)
                .bild(bild)
                .leckerheitsskala(leckerheitsskala)
                .dauer(dauer)
                .personen(personen)
                .build();
    }
}
