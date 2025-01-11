package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.repository.RezeptRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RezeptService {
    private final RezeptRepository repository;

    public List<Rezept> getAllRezepte(){
        return repository.findAll();
    }

    public Rezept getRezept(final int id){
        return repository.findById(id).get();
    }

    public Rezept insertRezept(final Rezept rezept){
        return repository.save(rezept);
    }

}
