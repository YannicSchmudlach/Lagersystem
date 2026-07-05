package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.EinkaufsladenLebensmittelDTO;
import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import com.schmudlach.lagersystem.service.EinkaufsladenLebensmittelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/einkaufsladenlebensmittel")
@RequiredArgsConstructor
@Slf4j
public class EinkaufsladenLebensmittelController {

    private final EinkaufsladenLebensmittelService service;

    @GetMapping()
    List<EinkaufsladenLebensmittel> getAll(){
        log.info("Alle Einkaufsladen-Lebensmittel-Zuordnungen wurden angefragt");
        List<EinkaufsladenLebensmittel> result = service.getAll();
        log.info("Einkaufsladen-Lebensmittel-Zuordnungen erfolgreich ermittelt, anzahl={}", result.size());
        return result;
    }

    @GetMapping(path = "/{id}")
    EinkaufsladenLebensmittel getEinkaufsladenLebensmittel(@PathVariable final int id){
        log.info("Einkaufsladen-Lebensmittel-Zuordnung wurde angefragt, id={}", id);
        EinkaufsladenLebensmittel result =  service.getEinkaufsladenLebensmittelById(id);
        log.info(
                "Einkaufsladen-Lebensmittel-Zuordnung erfolgreich ermittelt, id={}, einkaufsladenId={}, lebensmittelId={}",
                result.getEinkaufsladenLebensmittelId(),
                result.getEinkaufsladen().getEinkaufsladenId(),
                result.getLebensmittel().getLebensmittelID()
        );

        return result;
    }

    @PostMapping()
    ResponseEntity<Void> create(@RequestBody EinkaufsladenLebensmittelDTO einkaufsladenLebensmittelDTO) throws Exception {
        log.info(
                "Neue Einkaufsladen-Lebensmittel-Zuordnung soll erstellt werden, einkaufsladenId={}, lebensmittelId={}, preis={}",
                einkaufsladenLebensmittelDTO.einkaufsladenId(),
                einkaufsladenLebensmittelDTO.lebensmittelId(),
                einkaufsladenLebensmittelDTO.preis()
        );
        final var l = service.create(einkaufsladenLebensmittelDTO.einkaufsladenId(), einkaufsladenLebensmittelDTO.lebensmittelId(), einkaufsladenLebensmittelDTO.preis());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/einkaufsladenlebensmittel/" + l.getEinkaufsladenLebensmittelId());
        log.info(
                "Einkaufsladen-Lebensmittel-Zuordnung erfolgreich erstellt, id={}, einkaufsladenId={}, lebensmittelId={}, preis={}, location={}",
                l.getEinkaufsladenLebensmittelId(),
                l.getEinkaufsladen().getEinkaufsladenId(),
                l.getLebensmittel().getLebensmittelID(),
                l.getPreis(),
                location
        );
        return created(location).build();
    }
}
