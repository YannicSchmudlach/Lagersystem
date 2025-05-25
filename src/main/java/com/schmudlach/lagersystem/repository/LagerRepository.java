package com.schmudlach.lagersystem.repository;

import com.schmudlach.lagersystem.entity.Lager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LagerRepository extends JpaRepository<Lager, Integer> {
}
