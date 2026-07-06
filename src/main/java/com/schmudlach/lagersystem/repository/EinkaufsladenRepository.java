package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Einkaufsladen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EinkaufsladenRepository extends JpaRepository<Einkaufsladen,Integer> {
    Optional<Einkaufsladen> findByName(String name);
}
