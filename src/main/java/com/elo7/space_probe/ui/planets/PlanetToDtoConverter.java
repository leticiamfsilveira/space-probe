package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.domain.Planet;
import org.springframework.stereotype.Component;

@Component
class PlanetToDtoConverter {
    PlanetDTO convert(Planet planet) {
        return new PlanetDTO(planet.getId(), planet.getName());
    }
}
