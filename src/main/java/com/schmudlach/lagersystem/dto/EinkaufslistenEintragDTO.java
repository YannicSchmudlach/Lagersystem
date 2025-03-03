package com.schmudlach.lagersystem.dto;


import com.schmudlach.lagersystem.entity.EinkaufslistenEintrag;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Einkaufsliste;

public record EinkaufslistenEintragDTO(
        int menge,
        LebensmittelDTO lebensmittel
) {
    public EinkaufslistenEintrag toEinkaufslistenEintrag() {
        return EinkaufslistenEintrag.builder()
                .menge(menge)
                .lebensmittel(lebensmittel.toLebensmittel())
                .build();
    }


//    public EinkaufslistenEintragDTO(EinkaufslistenEintrag eintrag) {
//        this(eintrag.getMenge(), new LebensmittelDTO(eintrag.getLebensmittel()));
//    }
}


