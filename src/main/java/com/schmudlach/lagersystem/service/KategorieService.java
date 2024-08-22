package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.repository.KategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KategorieService {
    private final KategorieRepository repository;

    public List<Kategorie> getAllKategories(){
        return repository.findAll();
    }
    public Kategorie insertKategorie(Kategorie kategorie){
        return repository.save(kategorie);
    }
}
