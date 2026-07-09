package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.KategorieResponseDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KategorieMapper {
    public KategorieResponseDTO toResponseDTO(Kategorie kategorie) {
        return new KategorieResponseDTO(kategorie.getKategorieId(), kategorie.getName());
    }

    public List<KategorieResponseDTO> toResponseDTOs(List<Kategorie> kategorien) {
        return kategorien.stream().map(this::toResponseDTO).toList();
    }
}
