package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.LebensmittelDTO;
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
        return service.getAllRezepte();
    }

    @GetMapping(path = "{id}")
    Rezept getRezept(@PathVariable final int id){
        return service.getRezept(id);
    }

    @PostMapping
    ResponseEntity<Void> createRezept(@RequestBody final RezeptDTO rezeptDTO, final HttpServletRequest request) throws URISyntaxException {
        final var l = service.insertRezept(rezeptDTO.toRezept());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getRezeptId());
        return created(location).build();
    }
}
