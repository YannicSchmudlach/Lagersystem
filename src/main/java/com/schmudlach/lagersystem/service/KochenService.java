package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.model.BestandsPruefungResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class KochenService {
    private final VerfügbareLebensmittelService verfügbareLebensmittelService;
    private final RezeptService rezeptService;
    private final BestandsPruefungService bestandsPruefungService;

    public BestandsPruefungResult decrementVerfügbareLebensmittel(final int rezeptId) {

        final Rezept rezept = rezeptService.getRezept(rezeptId);

        final BestandsPruefungResult result = bestandsPruefungService.ueberfruefenObAlleLebensmittelVorhandenSind(rezept);

        if (!result.isSuccess())
            return result;

        for (RezeptLebensmittel obj : rezept.getRezeptLebensmittel()) {
            VerfügbareLebensmittel v = verfügbareLebensmittelService.getVerfügbareLebensmittelByLebensmittel(obj.getLebensmittel());
            v.setAnzahl(v.getAnzahl() - obj.getMenge());
        }

        return result;

    }

    @Transactional(readOnly = true)
    public List<Rezept> getAllKochbarenRezepte() {
        List<Rezept> allRezepte = rezeptService.getAllRezepte();

        final Map<Integer, Double> bestandByLebensmittelId = verfügbareLebensmittelService
                .getAllVerfügbareLebensmittel()
                .stream()
                .collect(Collectors.toMap(
                        v -> v.getLebensmittel().getLebensmittelID(),
                        VerfügbareLebensmittel::getAnzahl
                ));

        List<Rezept> kochbar = new ArrayList<>();

        for (Rezept rezept : allRezepte) {
            if (istRezeptKochbar(rezept, bestandByLebensmittelId)) {
                kochbar.add(rezept);
            }
        }
        return kochbar;
    }

    private boolean istRezeptKochbar(final Rezept rezept, final Map<Integer, Double> bestandByLebensmittelId) {
        for (RezeptLebensmittel rezeptLebensmittel : rezept.getRezeptLebensmittel()) {
            final int lebensmittelId = rezeptLebensmittel.getLebensmittel().getLebensmittelID();
            final double vorhanden = bestandByLebensmittelId.getOrDefault(lebensmittelId, 0.0);
            final double benoetigt = rezeptLebensmittel.getMenge();

            if (vorhanden < benoetigt) {
                return false;
            }
        }

        return true;
    }
}
