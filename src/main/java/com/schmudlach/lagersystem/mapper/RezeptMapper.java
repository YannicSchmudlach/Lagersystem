package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.RezeptResponseDTO;
import com.schmudlach.lagersystem.entity.Rezept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RezeptMapper {

    private final RezeptLebensmittelMapper rezeptLebensmittelMapper;

    public RezeptResponseDTO toResponseDTO(Rezept rezept) {
        return new RezeptResponseDTO(rezept.getRezeptId(), rezeptLebensmittelMapper.toResponseDTOs(rezept.getRezeptLebensmittel()), rezept.getBeschreibung(), rezept.getBild(), rezept.getLeckerheitsskala(), rezept.getDauer(), rezept.getPersonen());
    }

    public List<RezeptResponseDTO> toResponseDTOs(List<Rezept> rezepte) {
        return rezepte.stream().map(this::toResponseDTO).toList();
    }
}
