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
    public List<VerfügbareLebensmittel> getAll(){
        return service.getAllVerfügbareLebensmittel();
    }

    @GetMapping(path = "{id}")
    VerfügbareLebensmittel getVerfügbareLebensmittel(@PathVariable final int id){
        return service.getVerfügbareLebensmittelById(id);
    }

    @PostMapping
    ResponseEntity<Void> createVerfügbareLebensmittel(@RequestBody final VerfügbareLebensmittelDTO verfügbareLebensmittelDTO, final HttpServletRequest request) throws URISyntaxException {
        final var l = service.create(verfügbareLebensmittelDTO);
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/verfügbarelebensmittel/" + l.getVerfuegbareLebensmittelId());
        return created(location).build();
    }

}
