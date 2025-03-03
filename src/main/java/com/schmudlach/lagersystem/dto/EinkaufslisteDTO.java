package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Einkaufsliste;
import com.schmudlach.lagersystem.entity.EinkaufslistenEintrag;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;

import java.util.List;


public record EinkaufslisteDTO(
        List<EinkaufslistenEintragDTO> eintraege
) {
    public Einkaufsliste toEinkaufsliste() {
        return Einkaufsliste.builder()
                .eintraege(eintraege.stream()
                        .map(EinkaufslistenEintragDTO::toEinkaufslistenEintrag)
                        .toList())
                .build();
    }

//    public EinkaufslisteDTO(Einkaufsliste einkaufsliste) {
//        this(einkaufsliste.getEintraege()
//                .stream()
//                .map(EinkaufslistenEintragDTO::new)
//                .toList());
//    }
}


