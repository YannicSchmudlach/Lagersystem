package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Kategorie;
import com.schmudlach.lagersystem.entity.Lebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LebensmittelRepository extends JpaRepository<Lebensmittel,Integer> {
    Optional<Lebensmittel> findByName(String name);
}
