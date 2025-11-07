package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.error.ConflictException;
import com.schmudlach.lagersystem.error.NotFoundException;
import com.schmudlach.lagersystem.repository.EinkaufsladenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class EinkaufsladenService {

    private final EinkaufsladenRepository repository;


    public List<Einkaufsladen> getAllEinkaufsladen() {
        return repository.findAll();
    }

    public Einkaufsladen getEinkaufsladen(int id){
        return repository.findById(id).orElseThrow(()-> new NotFoundException("einkaufsladen",String.valueOf(id)));
    }

    @Transactional
    public Einkaufsladen insertEinkaufsladen(Einkaufsladen einkaufsladen) {

        repository.findByName(einkaufsladen.getName().toUpperCase(Locale.ROOT).trim()).ifPresent(tmp -> {
            throw new ConflictException(tmp.getName());
        });

        return repository.save(einkaufsladen);
    }
}
