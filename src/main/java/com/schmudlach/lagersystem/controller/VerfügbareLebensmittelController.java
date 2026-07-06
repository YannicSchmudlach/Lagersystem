package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.RezeptDTO;
import com.schmudlach.lagersystem.dto.VerfügbareLebensmittelDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.repository.VerfügbareLebensmittelRepository;
import com.schmudlach.lagersystem.service.VerfügbareLebensmittelService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/verfügbarelebensmittel")
public class VerfügbareLebensmittelController {
    private final VerfügbareLebensmittelService service;


    @GetMapping
    List<VerfügbareLebensmittel> getAll() {
        log.info("Alle verfügbaren Lebensmittel wurden angefragt");
        List<VerfügbareLebensmittel> result = service.getAllVerfügbareLebensmittel();
        log.info("Verfügbare Lebensmittel erfolgreich ermittelt, anzahl={}", result.size());

        return result;
    }

    @GetMapping(path = "/{id}")
    VerfügbareLebensmittel getVerfügbareLebensmittel(@PathVariable final int id) {
        log.info("Verfügbares Lebensmittel wurde angefragt, id={}", id);
        VerfügbareLebensmittel result = service.getVerfügbareLebensmittelById(id);
        log.info(
                "Verfügbares Lebensmittel erfolgreich ermittelt, id={}, lebensmittelId={}, anzahl={}, threshold={}",
                result.getVerfuegbareLebensmittelId(),
                result.getLebensmittel().getLebensmittelID(),
                result.getAnzahl(),
                result.getThreshold()
        );
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<Void> createVerfügbareLebensmittel(@RequestBody final VerfügbareLebensmittelDTO verfügbareLebensmittelDTO, final HttpServletRequest request) throws URISyntaxException {
        log.info(
                "Neues verfügbares Lebensmittel soll erstellt werden, lebensmittelId={}, anzahl={}, threshold={}",
                verfügbareLebensmittelDTO.lebensmittelId(),
                verfügbareLebensmittelDTO.anzahl(),
                verfügbareLebensmittelDTO.threshold()
        );
        final var l = service.create(verfügbareLebensmittelDTO);
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/verfügbarelebensmittel/" + l.getVerfuegbareLebensmittelId());
        log.info(
                "Verfügbares Lebensmittel erfolgreich erstellt, id={}, lebensmittelId={}, anzahl={}, threshold={}, location={}",
                l.getVerfuegbareLebensmittelId(),
                l.getLebensmittel().getLebensmittelID(),
                l.getAnzahl(),
                l.getThreshold(),
                location
        );
        return created(location).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteVerfügbareLebensmittel(@PathVariable final int id) {
        log.info("Verfügbares Lebensmittel soll gelöscht werden, id={}", id);
        service.deleteById(id);
        log.info("Verfügbares Lebensmittel erfolgreich gelöscht, id={}", id);
        return ResponseEntity.noContent().build();
    }

}
