package com.schmudlach.lagersystem.controller;


import com.schmudlach.lagersystem.dto.EinkaufsladenDTO;
import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.service.EinkaufsladenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/einkaufsladen")
@RequiredArgsConstructor
@Slf4j
public class EinkaufsladenController {

    private final EinkaufsladenService service;


    @GetMapping
    List<Einkaufsladen> getAllEinkaufsladen(){
        log.info("Alle Einkaufsläden wurden angefragt");
        List<Einkaufsladen> einkaufsladen =  service.getAllEinkaufsladen();
        log.info("Alle Einkaufsläden erfolgreich ermittelt, anzahl={}", einkaufsladen.size());

        return einkaufsladen;
    }

    @GetMapping(path = "/{id}")
    Einkaufsladen getEinkaufsladen(@PathVariable final int id){
        log.info("Einkaufsladen wurde angefragt, id={}", id);
        Einkaufsladen einkaufsladen = service.getEinkaufsladen(id);
        log.info("Einkaufsladen erfolgreich ermittelt, id={}, name={}",
                einkaufsladen.getEinkaufsladenId(),
                einkaufsladen.getName()
        );

        return einkaufsladen;
    }

    @PostMapping
    ResponseEntity<Void> insertEinkaufsladen(@RequestBody EinkaufsladenDTO einkaufsladenDTO) throws URISyntaxException {
        log.info("Neuer Einkaufsladen soll erstellt werden, name={}", einkaufsladenDTO.name());
        final var l = service.insertEinkaufsladen(einkaufsladenDTO.toEinkaufsladen());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/einkaufsladen/" + l.getEinkaufsladenId());
        log.info("Einkaufsladen erfolgreich erstellt, id={}, name={}, location={}",
                l.getEinkaufsladenId(),
                l.getName(),
                location
        );
        return created(location).build();
    }
}
