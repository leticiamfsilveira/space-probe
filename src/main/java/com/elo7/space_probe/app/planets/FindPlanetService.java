package com.elo7.space_probe.app.planets;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindPlanetService {
    private final Planets planets;

    FindPlanetService(Planets planets) {
        this.planets = planets;
    }

    public Optional<Planet> execute(Integer id) {
        return planets.findById(id);
    }

}
