package com.schmudlach.lagersystem.service;


import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.repository.EinkaufsladenLebensmittelRepository;
import com.schmudlach.lagersystem.repository.EinkaufsladenRepository;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EinkaufsladenLebensmittelService {

    private EinkaufsladenLebensmittelRepository repository;
    private LebensmittelRepository lebensmittelRepository;
    private EinkaufsladenRepository einkaufsladenRepository;


    public List<EinkaufsladenLebensmittel> getAll(){
        return repository.findAll();
    }

    public EinkaufsladenLebensmittel create(EinkaufsladenLebensmittel einkaufsladenLebensmittel){

        if (lebensmittelRepository.findByName(einkaufsladenLebensmittel.getLebensmittel().getName()).isEmpty()){
            return null;
        }
        if (einkaufsladenRepository.findByName(einkaufsladenLebensmittel.getEinkaufsladen().getName()).isEmpty()){
            return null;
        }

        return repository.save(einkaufsladenLebensmittel);

    }

}
