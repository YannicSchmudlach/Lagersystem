package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.dto.VerfügbareLebensmittelDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.error.BadRequestException;
import com.schmudlach.lagersystem.error.NotFoundException;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import com.schmudlach.lagersystem.repository.VerfügbareLebensmittelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class VerfügbareLebensmittelService {

    private final VerfügbareLebensmittelRepository repo;
    private final LebensmittelRepository lebensmittelRepository;


    public List<VerfügbareLebensmittel> getAllVerfügbareLebensmittel() {
        return repo.findAll();
    }

    public VerfügbareLebensmittel getVerfügbareLebensmittelById(int id){
        return repo.findById(id).orElseThrow(()-> new NotFoundException("VerfügbareLebensmittel",String.valueOf(id)));
    }

    @Transactional
    public VerfügbareLebensmittel create(VerfügbareLebensmittelDTO verfügbareLebensmittelDTO) {

        if (verfügbareLebensmittelDTO.anzahl() < 0)
            throw new BadRequestException("anzahl", String.valueOf(verfügbareLebensmittelDTO.anzahl()));

        if (verfügbareLebensmittelDTO.threshold() < 0)
            throw new BadRequestException("threshold", String.valueOf(verfügbareLebensmittelDTO.threshold()));

        Lebensmittel l = lebensmittelRepository.findById(verfügbareLebensmittelDTO.lebensmittelId()).orElseThrow(() -> new NotFoundException("lebensmittel", String.valueOf(verfügbareLebensmittelDTO.lebensmittelId())));
        VerfügbareLebensmittel v = VerfügbareLebensmittel.builder().lebensmittel(l).anzahl(verfügbareLebensmittelDTO.anzahl()).threshold(verfügbareLebensmittelDTO.threshold()).build();
        return repo.save(v);
    }

    @Transactional
    public VerfügbareLebensmittel deleteById(int id){
        VerfügbareLebensmittel v = getVerfügbareLebensmittelById(id);
        repo.deleteById(id);
        return v;
    }


}
