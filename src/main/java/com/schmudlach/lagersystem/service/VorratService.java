package com.schmudlach.lagersystem.service;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.Vorrat;
import com.schmudlach.lagersystem.repository.LebensmittelRepository;
import com.schmudlach.lagersystem.repository.VorratRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VorratService {

    private final VorratRepository vorratRepository;
    private final LebensmittelRepository lebensmittelRepository;

    public List<Vorrat> getAllVorrat(){
        return vorratRepository.findAll();
    }
    public Vorrat insertVorrat(Vorrat vorrat){
        return vorratRepository.save(vorrat);
    }
}
