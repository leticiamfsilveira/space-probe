package com.elo7.space_probe.app.planets;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.springframework.stereotype.Service;

@Service
public class CreatePlanetService {
    private final Planets planets;

    CreatePlanetService(Planets planets) {
        this.planets = planets;
    }

    public Planet execute(Planet planet) {
        return planets.save(planet);
    }

}
