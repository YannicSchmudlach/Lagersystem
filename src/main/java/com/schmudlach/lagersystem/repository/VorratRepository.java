package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Vorrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VorratRepository extends JpaRepository<Vorrat,Integer>{
}
