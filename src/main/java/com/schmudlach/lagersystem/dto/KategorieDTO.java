package com.schmudlach.lagersystem.dto;

import com.schmudlach.lagersystem.entity.Kategorie;

public record KategorieDTO(
        String name
) {
    public Kategorie toKategorie(){
        return Kategorie.builder().name(name).build();
    }
}
