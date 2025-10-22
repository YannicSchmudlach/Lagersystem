package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.repository.EinkaufsladenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EinkaufsladenService {

    private final EinkaufsladenRepository repository;


    public List<Einkaufsladen> getAllEinkaufsladen(){
        return repository.findAll();
    }

    public Einkaufsladen insertEinkaufsladen(Einkaufsladen einkaufsladen){
        return repository.save(einkaufsladen);
    }
}
