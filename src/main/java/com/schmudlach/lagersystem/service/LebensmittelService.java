package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.dto.LebensmittelDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.error.NotFoundException;
import com.schmudlach.lagersystem.repository.KategorieRepository;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LebensmittelService {

    private final LebensmittelRepository repository;
    private final KategorieRepository kategorieRepository;

    public Lebensmittel getLebensmittel(final int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Lebensmittel", String.valueOf(id)));
    }

    public List<Lebensmittel> getAllLebensmittel() {
        return repository.findAll();
    }

    @Transactional
    public Lebensmittel create(final LebensmittelDTO lebensmitteldto) {
        Kategorie kategorie = kategorieRepository.findById(lebensmitteldto.kategorieId()).orElseThrow(() ->
                new NotFoundException("Kategorie", String.valueOf(lebensmitteldto.kategorieId())));

        final String lebensmittelName = lebensmitteldto.name().toUpperCase(Locale.ROOT).trim();
        repository.findByName(lebensmittelName).ifPresent(tmp -> {
            throw new ConflictException(tmp.getName());
        });

        final Lebensmittel lebensmittel = Lebensmittel.builder().name(lebensmittelName).kategorie(kategorie).build();
        return repository.save(lebensmittel);
    }
}
