package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerfügbareLebensmittelRepository extends JpaRepository<VerfügbareLebensmittel, Integer> {
    Optional<VerfügbareLebensmittel> findByLebensmittel(Lebensmittel lebensmittel);
}
