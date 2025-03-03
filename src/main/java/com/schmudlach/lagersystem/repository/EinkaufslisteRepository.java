package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Einkaufsliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EinkaufslisteRepository extends JpaRepository<Einkaufsliste,Integer>{
}
