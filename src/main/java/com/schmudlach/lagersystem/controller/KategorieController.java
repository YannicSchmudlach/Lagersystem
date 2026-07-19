package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.apidesign.KategorieResponseDTO;
import com.schmudlach.lagersystem.dto.KategorieDTO;
import com.schmudlach.lagersystem.mapper.KategorieMapper;
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
    private final KategorieMapper kategorieMapper;

    @GetMapping()
    List<KategorieResponseDTO> getAll() {
        log.info("Alle Kategorien wurden angefragt");
        List<KategorieResponseDTO> result = kategorieMapper.toResponseDTOs(service.getAllKategories());
        log.info("Kategorien erfolgreich ermittelt, anzahl={}", result.size());
        return result;
    }

    @GetMapping(path = "/{id}")
    KategorieResponseDTO getKategorie(@PathVariable final int id) {
        log.info("Kategorie wurde angefragt, id={}", id);
        KategorieResponseDTO kategorie = kategorieMapper.toResponseDTO(service.getKategorieById(id));
        log.info("Kategorie erfolgreich ermittelt, id={}, name={}",
                kategorie.kategorieId(),
                kategorie.name()
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
