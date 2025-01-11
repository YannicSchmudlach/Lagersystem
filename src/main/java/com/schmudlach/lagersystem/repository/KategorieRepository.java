package com.schmudlach.lagersystem.repository;
import java.util.Optional;


import com.schmudlach.lagersystem.entity.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategorieRepository extends JpaRepository<Kategorie, Integer> {
    Optional<Kategorie> findByName(String name);
}
