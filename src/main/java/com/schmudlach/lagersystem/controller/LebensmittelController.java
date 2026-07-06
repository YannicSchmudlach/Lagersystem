package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.LebensmittelDTO;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.service.LebensmittelService;
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
@RequestMapping("/lebensmittel")
@RequiredArgsConstructor
@Slf4j
public class LebensmittelController {

    private final LebensmittelService service;

    @GetMapping(path = "/{id}")
    Lebensmittel getLebensmittel(@PathVariable final int id) {
        log.info("Lebensmittel wurde angefragt, id={}", id);
        Lebensmittel lebensmittel = service.getLebensmittel(id);
        log.info("Lebensmittel erfolgreich ermittelt, id={}, name={}",
                lebensmittel.getLebensmittelID(),
                lebensmittel.getName()
        );
        return lebensmittel;
    }

    @GetMapping
    List<Lebensmittel> getAllLebensmittel() {
        log.info("Alle Lebensmittel wurden angefragt");
        List<Lebensmittel> lebensmittel = service.getAllLebensmittel();
        log.info("Lebensmittel erfolgreich ermittelt, anzahl={}", lebensmittel.size());

        return lebensmittel;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<Void> createLebensmittel(@RequestBody final LebensmittelDTO lebensmittelDTO, final HttpServletRequest request) throws URISyntaxException {
        log.info("Neues Lebensmittel soll erstellt werden, name={}", lebensmittelDTO.name());
        final var l = service.create(lebensmittelDTO);
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "lebensmittel/" + l.getLebensmittelID());
        log.info("Lebensmittel erfolgreich erstellt, id={}, name={}, location={}",
                l.getLebensmittelID(),
                l.getName(),
                location
        );
        return created(location).build();
    }

}
