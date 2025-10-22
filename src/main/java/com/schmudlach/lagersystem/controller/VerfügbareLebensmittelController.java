package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import com.schmudlach.lagersystem.service.VerfügbareLebensmittelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VerfügbareLebensmittelController {
    private VerfügbareLebensmittelService service;


    @GetMapping
    public List<VerfügbareLebensmittel> getAll(){
        return service.getAllVerfügbareLebensmittel();
    }
}
