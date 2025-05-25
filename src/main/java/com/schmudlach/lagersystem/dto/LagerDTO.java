package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Lager;
import com.schmudlach.lagersystem.entity.Lebensmittel;

public record LagerDTO(
        LebensmittelDTO lebensmittel,
        double verfügbareMenge
) {
    public Lager toLager() {
        return Lager.builder().verfügbareMenge(verfügbareMenge).lebensmittel(lebensmittel.toLebensmittel()).build();
    }
}
