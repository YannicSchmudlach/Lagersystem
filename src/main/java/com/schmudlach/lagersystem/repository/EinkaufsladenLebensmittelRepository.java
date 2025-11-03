package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Einkaufsladen;
import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EinkaufsladenLebensmittelRepository extends JpaRepository<EinkaufsladenLebensmittel,Integer> {
        Optional<EinkaufsladenLebensmittel> findByLebensmittelAndEinkaufsladen(Lebensmittel lebensmittel, Einkaufsladen einkaufsladen);
}
