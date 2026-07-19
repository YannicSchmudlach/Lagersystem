package com.schmudlach.lagersystem.controller;


import com.schmudlach.lagersystem.apidesign.EinkaufsladenResponseDTO;
import com.schmudlach.lagersystem.dto.EinkaufsladenDTO;
import com.schmudlach.lagersystem.mapper.EinkaufsladenMapper;
import com.schmudlach.lagersystem.service.EinkaufsladenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final EinkaufsladenMapper einkaufsladenMapper;

    @GetMapping
    List<EinkaufsladenResponseDTO> getAllEinkaufsladen() {
        log.info("Alle Einkaufsläden wurden angefragt");
        List<EinkaufsladenResponseDTO> einkaufsladen = einkaufsladenMapper.toResponseDTOs(service.getAllEinkaufsladen());
        log.info("Alle Einkaufsläden erfolgreich ermittelt, anzahl={}", einkaufsladen.size());

        return einkaufsladen;
    }

    @GetMapping(path = "/{id}")
    EinkaufsladenResponseDTO getEinkaufsladen(@PathVariable final int id) {
        log.info("Einkaufsladen wurde angefragt, id={}", id);
        EinkaufsladenResponseDTO einkaufsladen = einkaufsladenMapper.toResponseDTO(service.getEinkaufsladen(id));
        log.info("Einkaufsladen erfolgreich ermittelt, id={}, name={}",
                einkaufsladen.einkaufsladenId(),
                einkaufsladen.name()
        );

        return einkaufsladen;
    }

    @PreAuthorize("hasRole('ADMIN')")
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
