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
        return service.getAllEinkaufsladen();
    }

    @PostMapping
    ResponseEntity<Void> insertEinkaufsladen(@RequestBody EinkaufsladenDTO einkaufsladenDTO) throws URISyntaxException {
        final var l = service.insertEinkaufsladen(einkaufsladenDTO.toEinkaufsladen());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getEinkaufsladenId());
        return created(location).build();
    }
}
