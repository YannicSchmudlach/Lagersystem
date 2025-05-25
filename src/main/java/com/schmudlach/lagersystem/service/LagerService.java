package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lager;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.repository.LagerRepository;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LagerService {
    private final LagerRepository repository;
    private final LebensmittelRepository lebensmittelRepository;

    public List<Lager> getAllLager() {
        return repository.findAll();
    }

    public Lager insertLebensmittelIntoLager(Lager lager) {

        Lebensmittel lebensmittel = lebensmittelRepository.findByName(lager.getLebensmittel().getKategorie().getName())
                .orElseGet(() -> {
                    Lebensmittel neueLebensmittel = Lebensmittel.builder()
                            .name(lager.getLebensmittel().getName())
                            .preis(lager.getLebensmittel().getPreis())
                            .einkaufsLaden(lager.getLebensmittel().getEinkaufsLaden())
                            .einheit(lager.getLebensmittel().getEinheit())
                            .threshold(lager.getLebensmittel().getThreshold())
                            .kategorie(lager.getLebensmittel().getKategorie())
                            .build();
                    return lebensmittelRepository.save(neueLebensmittel);
                });

        lager.setLebensmittel(lebensmittel);
        return repository.save(lager);
    }

    public Lager getLagerById(int id) {
        return repository.findById(id).get();
    }

    public Lager updateMengeLager(Lager updateValue) {
        Lager dbLager = getLagerById(updateValue.getLagerID());
        dbLager.setVerfügbareMenge(updateValue.getVerfügbareMenge());
        return repository.save(dbLager);
    }
}
