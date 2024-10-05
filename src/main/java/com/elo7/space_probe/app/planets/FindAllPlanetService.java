package com.elo7.space_probe.app.planets;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPlanetService {
    private final Planets planets;

    FindAllPlanetService(Planets planets) {
        this.planets = planets;
    }

    public List<Planet> execute() {
        return planets.findAll();
    }

}
