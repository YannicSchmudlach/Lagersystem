package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.EinkaufsladenLebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EinkaufsladenLebensmittelRepository extends JpaRepository<EinkaufsladenLebensmittel,Integer> {
}
