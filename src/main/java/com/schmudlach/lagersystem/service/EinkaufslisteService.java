package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.dto.EinkaufslisteDTO;
import com.schmudlach.lagersystem.entity.Einkaufsliste;
import com.schmudlach.lagersystem.entity.EinkaufslistenEintrag;
import com.schmudlach.lagersystem.repository.EinkaufslisteRepository;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EinkaufslisteService {

    private final EinkaufslisteRepository repository;
    private final LebensmittelRepository lebensmittelRepository;

    public Einkaufsliste getEinkaufsliste(final int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Einkaufsliste nicht gefunden."));
    }

    public Einkaufsliste create(Einkaufsliste einkaufsliste) {
        return repository.save(einkaufsliste);
    }

    public Einkaufsliste updateEinkaufsliste(int id, Einkaufsliste updatedEinkaufsliste) {
        return repository.findById(id).map(existingEinkaufsliste -> {
            existingEinkaufsliste.setEintraege(updatedEinkaufsliste.getEintraege());
            return repository.save(existingEinkaufsliste);
        }).orElseThrow(() -> new RuntimeException("Einkaufsliste mit ID " + id + " nicht gefunden."));
    }

    public void deleteEinkaufsliste(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Einkaufsliste mit ID " + id + " nicht gefunden.");
        }
        repository.deleteById(id);
    }

    public Einkaufsliste addEntryToEinkaufsliste(int id, EinkaufslistenEintrag einkaufslistenEintrag) {
        return repository.findById(id).map(einkaufsliste -> {
            if (einkaufsliste.getEintraege() == null) {
                einkaufsliste.setEintraege(new ArrayList<>());
            }
            einkaufsliste.getEintraege().add(einkaufslistenEintrag);
            return repository.save(einkaufsliste);
        }).orElseThrow(() -> new RuntimeException("Einkaufsliste mit ID " + id + " nicht gefunden."));
    }
}
