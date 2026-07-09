package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.EinkaufsladenLebensmittelResponseDTO;
import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EinkaufsladenLebensmittelMapper {
    private final LebensmittelMapper lebensmittelMapper;
    private final EinkaufsladenMapper einkaufsladenMapper;

    public EinkaufsladenLebensmittelResponseDTO toResponseDTO(final EinkaufsladenLebensmittel e){
        return new EinkaufsladenLebensmittelResponseDTO(e.getEinkaufsladenLebensmittelId(),lebensmittelMapper.toResponseDTO(e.getLebensmittel()),einkaufsladenMapper.toResponseDTO(e.getEinkaufsladen()),e.getPreis());
    }

    public List<EinkaufsladenLebensmittelResponseDTO> toResponseDTOs(final List<EinkaufsladenLebensmittel>e){
        return e.stream().map(this::toResponseDTO).toList();
    }
}
