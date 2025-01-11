package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Rezept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezeptRepository extends JpaRepository<Rezept,Integer> {
}
