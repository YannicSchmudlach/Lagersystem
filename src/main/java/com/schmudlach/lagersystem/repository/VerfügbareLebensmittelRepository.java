package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.VerfügbareLebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerfügbareLebensmittelRepository extends JpaRepository<VerfügbareLebensmittel, VerfügbareLebensmittel> {
}
