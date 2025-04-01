package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import com.schmudlach.lagersystem.repository.RezeptRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RezeptService {
    private final RezeptRepository repository;
    private final LebensmittelRepository lebensmittelRepository;
    private final LebensmittelService lebensmittelService;

    public List<Rezept> getAllRezepte(){
        return repository.findAll();
    }

    public Rezept getRezept(final int id){
        return repository.findById(id).get();
    }

    public Rezept insertRezept(final Rezept rezept){

        List<Lebensmittel> neueLebensmittel = new ArrayList<>();

        for (Lebensmittel lebensmittel : rezept.getLebensmittelList()) {
            Lebensmittel gespeichertesLebensmittel = lebensmittelRepository.findByName(lebensmittel.getName())
                    .orElseGet(() -> lebensmittelService.create(lebensmittel));
            neueLebensmittel.add(gespeichertesLebensmittel);
        }

        rezept.setLebensmittelList(neueLebensmittel);
        return repository.save(rezept);
    }
}
