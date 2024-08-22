package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.KategorieDTO;
import com.schmudlach.lagersystem.dto.LebensmittelDTO;
import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.service.KategorieService;
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
@RequestMapping("/kategorie")
@RequiredArgsConstructor
@Slf4j
public class KategorieController {
    private final KategorieService service;

    @GetMapping()
    List<Kategorie> getAll(){
        return service.getAllKategories();
    }
    @PostMapping()
    ResponseEntity<Void> createKategorie(@RequestBody final KategorieDTO kategorieDTO, final HttpServletRequest request) throws URISyntaxException {
        final var  l = service.insertKategorie(kategorieDTO.toKategorie());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getKategorieId()); //NOSONAR
        return created(location).build();
    }
}
