package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.EinkaufsladenResponseDTO;
import com.schmudlach.lagersystem.entity.Einkaufsladen;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EinkaufsladenMapper {
    public EinkaufsladenResponseDTO toResponseDTO(Einkaufsladen einkaufsladen) {
        return new EinkaufsladenResponseDTO(einkaufsladen.getEinkaufsladenId(), einkaufsladen.getName());
    }

    public List<EinkaufsladenResponseDTO> toResponseDTOs(List<Einkaufsladen> einkaufsladen) {
        return einkaufsladen.stream().map(this::toResponseDTO).toList();
    }
}
