package com.elo7.space_probe.infra.repository;

import com.elo7.space_probe.domain.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPlanetRepository extends JpaRepository<Planet, Integer> { }
