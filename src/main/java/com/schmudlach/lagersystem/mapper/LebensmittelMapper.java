package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.LebensmittelResponseDTO;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LebensmittelMapper {
    private final KategorieMapper kategorieMapper;

    public LebensmittelResponseDTO toResponseDTO(final Lebensmittel lebensmittel) {
        return new LebensmittelResponseDTO(lebensmittel.getLebensmittelID(), lebensmittel.getName(), kategorieMapper.toResponseDTO(lebensmittel.getKategorie()));
    }

    public List<LebensmittelResponseDTO> toResponseDTOs(final List<Lebensmittel> lebensmittelList) {
        return lebensmittelList.stream().map(this::toResponseDTO).toList();
    }
}
