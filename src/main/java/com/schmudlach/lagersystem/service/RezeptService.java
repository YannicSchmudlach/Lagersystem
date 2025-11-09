package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.dto.RezeptDTO;
import com.schmudlach.lagersystem.dto.RezeptLebensmittelDTO;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import com.schmudlach.lagersystem.error.BadRequestException;
import com.schmudlach.lagersystem.error.NotFoundException;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import com.schmudlach.lagersystem.repository.RezeptRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RezeptService {
    private final RezeptRepository repository;
    private final LebensmittelRepository lebensmittelRepository;

    public List<Rezept> getAllRezepte() {
        return repository.findAll();
    }

    public Rezept getRezept(final int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Rezept", String.valueOf(id)));
    }

    @Transactional
    public Rezept insertRezept(final RezeptDTO rezeptDTO) {
        if (!(rezeptDTO.leckerheitsskala() >= 0 && rezeptDTO.leckerheitsskala() <= 10))
            throw new BadRequestException("leckerheitsskala", String.valueOf(rezeptDTO.leckerheitsskala()));

        if (rezeptDTO.beschreibung().trim().isBlank())
            throw new BadRequestException("beschreibung", rezeptDTO.beschreibung());

        if (rezeptDTO.rezeptLebensmittelList() == null || rezeptDTO.rezeptLebensmittelList().size() <= 2)
            throw new BadRequestException("rezeptLebensmittelList", "Mit 2 oder weniger Lebensmittel");

        if (rezeptDTO.personen() <= 0)
            throw new BadRequestException("personen", String.valueOf(rezeptDTO.personen()));

        if (rezeptDTO.dauer()<=0){
            throw new BadRequestException("dauer", String.valueOf(rezeptDTO.dauer()));
        }

        HashMap<Integer, Double> ids = new HashMap<Integer, Double>();

        for (RezeptLebensmittelDTO obj : rezeptDTO.rezeptLebensmittelList()) {
            double m = obj.menge();
            if (m<0)
                throw new BadRequestException("menge", "0");
            if (obj.einheit().trim().isBlank())
                throw new BadRequestException("einheit", "null");
            ids.put(obj.lebensmittelId(), m);
        }

        if (ids.size() != rezeptDTO.rezeptLebensmittelList().size())
            throw new BadRequestException("Lebensmittel", "waren doppelt");


        List<Lebensmittel> lebensmittelList = lebensmittelRepository.findAllById(ids.keySet());
        if (lebensmittelList.size() != ids.size())
            throw new NotFoundException("Lebensmittel");

        Rezept rezept = Rezept.builder().dauer(rezeptDTO.dauer()).beschreibung(rezeptDTO.beschreibung()).bild(rezeptDTO.bild()).personen(rezeptDTO.personen()).leckerheitsskala(rezeptDTO.leckerheitsskala()).build();

        List<RezeptLebensmittel> rezeptLebensmittelList = new ArrayList<>();

        for (Lebensmittel leb : lebensmittelList) {
            rezeptLebensmittelList.add(RezeptLebensmittel.builder().menge(ids.get(leb.getLebensmittelID())).lebensmittel(leb).rezept(rezept).build());
        }

        rezept.setRezeptLebensmittel(rezeptLebensmittelList);

        return repository.save(rezept);
    }
}
