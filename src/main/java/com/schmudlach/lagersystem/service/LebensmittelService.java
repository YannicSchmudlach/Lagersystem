package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LebensmittelService {

    private final LebensmittelRepository repository;

    public Lebensmittel getLebensmittel(final int id){
        return repository.findById(id).get();
    }

    public List<Lebensmittel> getAllLebensmittel(){
        return repository.findAll();
    }
    public Lebensmittel create(final Lebensmittel lebensmittel){
        return repository.save(lebensmittel);
    }
}
