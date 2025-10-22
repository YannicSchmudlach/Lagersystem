package com.schmudlach.lagersystem.controller;


import com.schmudlach.lagersystem.dto.EinkaufsladenDTO;
import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.service.EinkaufsladenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Einkaufsladen insertEinkaufsladen(@RequestBody EinkaufsladenDTO einkaufsladenDTO){
        return service.insertEinkaufsladen(einkaufsladenDTO.toEinkaufsladen());
    }
}
