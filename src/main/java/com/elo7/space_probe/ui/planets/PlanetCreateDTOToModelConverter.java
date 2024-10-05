package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.domain.Planet;
import org.springframework.stereotype.Component;

@Component
class PlanetCreateDTOToModelConverter {
    public Planet convert(PlanetCreateDTO planetCreateDTO) {
        return new Planet(planetCreateDTO.name(), planetCreateDTO.width(), planetCreateDTO.height());
    }
}
