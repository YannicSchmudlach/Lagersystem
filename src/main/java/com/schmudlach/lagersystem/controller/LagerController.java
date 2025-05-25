package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.LagerDTO;
import com.schmudlach.lagersystem.entity.Lager;
import com.schmudlach.lagersystem.service.LagerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.ResponseEntity.created;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/lager")
@RequiredArgsConstructor
@Slf4j
public class LagerController {
    private final LagerService service;

    @GetMapping
    List<Lager> getAll(){
        return service.getAllLager();
    }

    @PostMapping
    ResponseEntity<Void> createLager(@RequestBody final LagerDTO lagerDTO, final HttpServletRequest request) throws URISyntaxException {
        final var l = service.insertLebensmittelIntoLager(lagerDTO.toLager());
        final var location = new URI(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()+'/'+l.getLagerID());
        return created(location).build();
    }
}
