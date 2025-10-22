package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.dto.EinkaufsladenLebensmittelDTO;
import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import com.schmudlach.lagersystem.service.EinkaufsladenLebensmittelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    EinkaufsladenLebensmittel create(@RequestBody EinkaufsladenLebensmittelDTO einkaufsladenLebensmittelDTO) throws Exception {
        return service.create(einkaufsladenLebensmittelDTO.toEinkaufsladenLebensmittel());
    }
}
