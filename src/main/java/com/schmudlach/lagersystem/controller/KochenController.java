package com.schmudlach.lagersystem.controller;

import com.schmudlach.lagersystem.entity.Rezept;
import com.schmudlach.lagersystem.model.BestandsPruefungResult;
import com.schmudlach.lagersystem.service.KochenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kochen")
@RequiredArgsConstructor
@Slf4j
public class KochenController {
    private final KochenService service;


    @GetMapping
    List<Rezept> getAllKochbarenRezepte(){
        log.info("anfrage an alle Kochbaren Rezepte");
        List<Rezept> rezepte =service.getAllKochbarenRezepte();
        log.info("Kochbare Rezepte erfolgreich ermittelt, anzahl={}", rezepte.size());
        return rezepte;
    }


    @PostMapping(path = "/{rezeptId}")
    ResponseEntity<BestandsPruefungResult> kochen(@PathVariable final int rezeptId){
        log.info("Kochen angefragt für rezeptId={}", rezeptId);
        BestandsPruefungResult data = service.decrementVerfügbareLebensmittel(rezeptId);
        if (data.isSuccess()){
            log.info("Rezept erfolgreich gekocht für rezeptId={}", rezeptId);
            return ResponseEntity.ok(data);
        }
        log.warn("Rezept konnte nicht gekocht werden für rezeptId={}, grund={}, probleme={}",
                rezeptId,
                data.getErrorMessage(),
                data.getProbleme().size()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(data);
    }
}
