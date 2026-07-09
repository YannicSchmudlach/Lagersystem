package com.schmudlach.lagersystem.mapper;

import com.schmudlach.lagersystem.apidesign.VerfügbareLebensmittelResponseDTO;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VerfügbareLebensmittelMapper {
    private final LebensmittelMapper lebensmittelMapper;

    public VerfügbareLebensmittelResponseDTO toResponseDTO(final VerfügbareLebensmittel v){
        return new VerfügbareLebensmittelResponseDTO(v.getVerfuegbareLebensmittelId(),lebensmittelMapper.toResponseDTO(v.getLebensmittel()),v.getAnzahl(),v.getThreshold());
    }
    public List<VerfügbareLebensmittelResponseDTO>toResponseDTOs(final List<VerfügbareLebensmittel>v){
        return v.stream().map(this::toResponseDTO).toList();
    }

}
