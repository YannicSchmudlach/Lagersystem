package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.KategorieDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.service.KategorieService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/kategorie")
@RequiredArgsConstructor
@Slf4j
public class KategorieController {
    private final KategorieService service;

    @GetMapping()
    List<Kategorie> getAll() {
        log.info("Alle Kategorien wurden angefragt");
        List<Kategorie> result = service.getAllKategories();
        log.info("Kategorien erfolgreich ermittelt, anzahl={}", result.size());
        return result;
    }

    @GetMapping(path = "/{id}")
    Kategorie getKategorie(@PathVariable final int id) {
        log.info("Kategorie wurde angefragt, id={}", id);
        Kategorie kategorie = service.getKategorieById(id);
        log.info("Kategorie erfolgreich ermittelt, id={}, name={}",
                kategorie.getKategorieId(),
                kategorie.getName()
        );

        return kategorie;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<Void> createKategorie(@RequestBody final KategorieDTO kategorieDTO, final HttpServletRequest request) throws URISyntaxException {
        log.info("Neue Kategorie soll erstellt werden, name={}", kategorieDTO.name());
        final var l = service.insertKategorie(kategorieDTO.toKategorie());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/kategorie/" + l.getKategorieId());
        log.info("Kategorie erfolgreich erstellt, id={}, name={}, location={}",
                l.getKategorieId(),
                l.getName(),
                location
        );

        return created(location).build();
    }
}
