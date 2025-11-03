package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.repository.KategorieRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class KategorieService {
    private final KategorieRepository repository;

    public List<Kategorie> getAllKategories() {
        return repository.findAll();
    }

    @Transactional
    public Kategorie insertKategorie(Kategorie kategorie) {
        repository.findByName(kategorie.getName().toUpperCase(Locale.ROOT).trim()).ifPresent(tmp -> {
            throw new ConflictException(tmp.getName());
        });
        return repository.save(kategorie);
    }
}
