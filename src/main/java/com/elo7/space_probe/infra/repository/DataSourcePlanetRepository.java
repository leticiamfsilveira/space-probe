package com.elo7.space_probe.infra.repository;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataSourcePlanetRepository implements Planets {
    private final SpringDataPlanetRepository repository;

    public DataSourcePlanetRepository(SpringDataPlanetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Planet save(Planet planet) {
        return repository.save(planet);
    }

    @Override
    public Optional<Planet> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Planet> findAll() {
        return repository.findAll();
    }
}
