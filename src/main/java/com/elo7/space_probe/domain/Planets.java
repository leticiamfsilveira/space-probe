package com.elo7.space_probe.domain;

import java.util.List;
import java.util.Optional;

public interface Planets {
    Planet save(Planet planet);

    Optional<Planet> findById(Integer id);

    List<Planet> findAll();
}
