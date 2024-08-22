package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorieRepository extends JpaRepository<Kategorie, Integer> {
}
