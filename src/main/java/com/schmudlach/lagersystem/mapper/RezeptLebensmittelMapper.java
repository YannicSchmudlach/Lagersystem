package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.RezeptLebensmittelResponseDTO;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RezeptLebensmittelMapper {
    private final LebensmittelMapper lebensmittelMapper;

    public RezeptLebensmittelResponseDTO toResponseDTO(RezeptLebensmittel rezeptLebensmittel) {
        return new RezeptLebensmittelResponseDTO(rezeptLebensmittel.getRezeptLebensmittelId(), lebensmittelMapper.toResponseDTO(rezeptLebensmittel.getLebensmittel()), rezeptLebensmittel.getMenge(), rezeptLebensmittel.getEinheit());
    }

    public List<RezeptLebensmittelResponseDTO> toResponseDTOs(List<RezeptLebensmittel> r) {
        return r.stream().map(this::toResponseDTO).toList();
    }
}
