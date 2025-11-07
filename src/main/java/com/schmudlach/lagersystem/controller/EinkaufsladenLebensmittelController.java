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
        return service.getAll();
    }

    @PostMapping()
    ResponseEntity<Void> create(@RequestBody EinkaufsladenLebensmittelDTO einkaufsladenLebensmittelDTO) throws Exception {
        final var l = service.create(einkaufsladenLebensmittelDTO.einkaufsladenId(), einkaufsladenLebensmittelDTO.lebensmittelId(), einkaufsladenLebensmittelDTO.preis());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getEinkaufsladenLebensmittelId());
        return created(location).build();
    }
}
