package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.repository.VerfügbareLebensmittelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VerfügbareLebensmittelService {

    private VerfügbareLebensmittelRepository repo;


    public List<VerfügbareLebensmittel> getAllVerfügbareLebensmittel(){
        return repo.findAll();
    }

    public VerfügbareLebensmittel create(VerfügbareLebensmittel verfügbareLebensmittel){
        var res = repo.save(verfügbareLebensmittel);
        return res;
    }



}
