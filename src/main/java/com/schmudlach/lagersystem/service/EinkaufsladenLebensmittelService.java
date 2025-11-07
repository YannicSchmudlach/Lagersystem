package com.schmudlach.lagersystem.service;


import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.error.BadRequestException;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.error.NotFoundException;
import com.schmudlach.lagersystem.repository.EinkaufsladenLebensmittelRepository;
import com.schmudlach.lagersystem.repository.EinkaufsladenRepository;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EinkaufsladenLebensmittelService {

    private final EinkaufsladenLebensmittelRepository repository;
    private final LebensmittelRepository lebensmittelRepository;
    private final EinkaufsladenRepository einkaufsladenRepository;

    public List<EinkaufsladenLebensmittel> getAll() {
        return repository.findAll();
    }

    public EinkaufsladenLebensmittel getEinkaufsladenLebensmittelById(int id){
        return repository.findById(id).orElseThrow(()-> new NotFoundException("einkaufsladenLebensmitttel",String.valueOf(id)));
    }

    @Transactional
    public EinkaufsladenLebensmittel create(final int einkaufsladenId, final int lebensmittelId, final double preis) {
        Lebensmittel lebensmittel = lebensmittelRepository.findById(lebensmittelId).orElseThrow(() -> new NotFoundException("Lebensmittel", String.valueOf(lebensmittelId)));
        Einkaufsladen einkaufsladen = einkaufsladenRepository.findById(einkaufsladenId).orElseThrow(() -> new NotFoundException("Einkaufsladen", String.valueOf(einkaufsladenId)));

        if (!(preis > 0.0))
            throw new BadRequestException("preis", String.valueOf(preis));


        repository.findByLebensmittelAndEinkaufsladen(lebensmittel, einkaufsladen).ifPresent(tmp -> {
            throw new ConflictException(lebensmittel.getName(), einkaufsladen.getName());
        });

        EinkaufsladenLebensmittel einkaufsladenLebensmittel = EinkaufsladenLebensmittel.builder().einkaufsladen(einkaufsladen).lebensmittel(lebensmittel).preis(preis).build();
        return repository.save(einkaufsladenLebensmittel);

    }

}
