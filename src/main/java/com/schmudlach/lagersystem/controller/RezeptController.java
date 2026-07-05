package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.RezeptDTO;
import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.service.RezeptService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/rezept")
@RequiredArgsConstructor
@Slf4j
public class RezeptController {
    private final RezeptService service;

    @GetMapping
    List<Rezept> getAllRezepte(){
        log.info("Alle Rezepte wurden angefragt");
        List<Rezept> rezepte = service.getAllRezepte();
        log.info("Rezepte erfolgreich ermittelt, anzahl={}", rezepte.size());

        return rezepte;
    }

    @GetMapping(path = "/{id}")
    Rezept getRezept(@PathVariable final int id){
        log.info("Rezept wurde angefragt, id={}", id);

        Rezept rezept = service.getRezept(id);
        log.info("Rezept erfolgreich ermittelt, id={}", rezept.getRezeptId());

        return rezept;
    }

    @PostMapping
    ResponseEntity<Void> createRezept(@RequestBody final RezeptDTO rezeptDTO, final HttpServletRequest request) throws URISyntaxException {
        log.info("Neues Rezept soll erstellt werden");
        final var l = service.insertRezept(rezeptDTO);
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/rezept/" + l.getRezeptId());
        log.info("Rezept erfolgreich erstellt, id={}, location={}",
                l.getRezeptId(),
                location
        );
        return created(location).build();
    }

}
