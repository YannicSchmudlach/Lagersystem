package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.repository.KategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class KategorieService {
    private final KategorieRepository repository;

    public List<Kategorie> getAllKategories(){
        return repository.findAll();
    }
    public Kategorie insertKategorie(Kategorie kategorie){
        return repository.save(kategorie);
    }
}
