package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.entity.Vorrat;
import com.schmudlach.lagersystem.service.VorratService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vorrat")
@RequiredArgsConstructor
@Slf4j
public class VorratController {
    private final VorratService service;

    @GetMapping
    List<Vorrat> getAllVorrat(){
        return service.getAllVorrat();
    }
}
