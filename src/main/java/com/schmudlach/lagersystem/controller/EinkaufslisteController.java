package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.EinkaufslisteDTO;
import com.schmudlach.lagersystem.dto.EinkaufslistenEintragDTO;
import com.schmudlach.lagersystem.dto.LebensmittelDTO;
import com.schmudlach.lagersystem.entity.Einkaufsliste;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.service.EinkaufslisteService;
import com.schmudlach.lagersystem.service.LebensmittelService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/einkaufsliste")
@RequiredArgsConstructor
@Slf4j
public class EinkaufslisteController {

    private final EinkaufslisteService service;

    @GetMapping(path = "{id}")
    Einkaufsliste getEinkaufsliste(@PathVariable final int id) {
        return service.getEinkaufsliste(id);
    }
//TODO kein plan
//    @PostMapping
//    ResponseEntity<EinkaufslisteDTO> createEinkaufsliste(@RequestBody final EinkaufslistenEintragDTO einkaufslisteeintragDTO, final HttpServletRequest request) throws URISyntaxException {
//        final var  l = service.create(einkaufslisteeintragDTO.toEinkaufslistenEintrag());
//        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getEinkaufslisteId()); //NOSONAR
//        return created(location).build();
//    }
    @PutMapping(path = "{id}")
    public ResponseEntity<Einkaufsliste> updateEinkaufsliste(@PathVariable final int id, @RequestBody final EinkaufslisteDTO dto) {
        final Einkaufsliste updatedList = service.updateEinkaufsliste(id, dto.toEinkaufsliste());
        return ResponseEntity.ok(updatedList);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteEinkaufsliste(@PathVariable final int id) {
        service.deleteEinkaufsliste(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/entries")
    public ResponseEntity<Einkaufsliste> addEntryToEinkaufsliste(
            @PathVariable final int id,
            @RequestBody final EinkaufslistenEintragDTO eintragDTO) {
        final Einkaufsliste updatedList = service.addEntryToEinkaufsliste(id, eintragDTO.toEinkaufslistenEintrag());
        return ResponseEntity.ok(updatedList);
    }


}
