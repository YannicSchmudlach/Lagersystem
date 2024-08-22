package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.LebensmittelDTO;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.service.LebensmittelService;
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
@RequestMapping("/lebensmittel")
@RequiredArgsConstructor
@Slf4j
public class LebensmittelController {

    private final LebensmittelService service;
    @GetMapping(path = "{id}")
    Lebensmittel getLebensmittel(@PathVariable final int id){
        return service.getLebensmittel(id);
    }

    @GetMapping
    List<Lebensmittel> getAllLebensmittel(){
        return service.getAllLebensmittel();
    }

    @PostMapping
    ResponseEntity<Void> createLebensmittel(@RequestBody final LebensmittelDTO lebensmittelDTO, final HttpServletRequest request) throws URISyntaxException {
        final var  l = service.create(lebensmittelDTO.toLebensmittel());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + l.getLebensmittelID()); //NOSONAR
        return created(location).build();
    }

}
