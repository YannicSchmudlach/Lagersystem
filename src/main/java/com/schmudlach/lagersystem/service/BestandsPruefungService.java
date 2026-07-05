package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.entity.RezeptLebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.model.BestandsProblem;
import com.schmudlach.lagersystem.model.BestandsPruefungResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class BestandsPruefungService {
    private final VerfügbareLebensmittelService verfügbareLebensmittelService;

    public BestandsPruefungResult ueberfruefenObAlleLebensmittelVorhandenSind(final Rezept rezept) {
        List<BestandsProblem> probleme = new ArrayList<>();


        for (RezeptLebensmittel obj : rezept.getRezeptLebensmittel()) {

            final Lebensmittel lebensmittel = obj.getLebensmittel();
            final double benoetigt = obj.getMenge();

            final double vorhanden = verfügbareLebensmittelService.findByLebensmittel(lebensmittel)
                    .map(VerfügbareLebensmittel::getAnzahl)
                    .orElse(0.0);

            if (vorhanden < benoetigt)
                probleme.add(new BestandsProblem(lebensmittel, benoetigt, vorhanden));
        }

        if (!probleme.isEmpty())
            return BestandsPruefungResult.failed(
                    "Leider sind nicht genügend Lebensmittel im Vorrat vorhanden.",
                    probleme
            );

        return BestandsPruefungResult.success();
    }
}
