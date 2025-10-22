package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Einkaufsladen;

public record EinkaufsladenDTO(
        String name
) {

    public Einkaufsladen toEinkaufsladen(){
        return Einkaufsladen.builder().name(name).build();
    }
}
