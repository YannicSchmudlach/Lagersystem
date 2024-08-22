package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Lebensmittel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LebensmittelRepository extends JpaRepository<Lebensmittel,Integer> {
}
