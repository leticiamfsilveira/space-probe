package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.InvalidMovementException;
import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateProbePositionService {
    private final Probes probes;
    private final Planets planets;

    public ValidateProbePositionService(Probes probes, Planets planets) {
        this.probes = probes;
        this.planets = planets;
    }

    public void execute(Integer x, Integer y, Integer planetId)  {
        Planet planet = planets.findById(planetId).orElseThrow(() -> new ResourceNotFoundException("Planeta não encontrado"));

        if (!(x >= 0 && x <= planet.getWidth() && y >= 0 && y <= planet.getHeight())) {
            throw new InvalidMovementException("Movimentação inválida: fora dos limites do planeta");
        }

        probes.findAll().forEach(p -> {
            if (Objects.equals(p.getXPosition(), x) && Objects.equals(p.getYPosition(), y) && Objects.equals(p.getPlanetId(), planet.getId())) {
                throw new InvalidMovementException("Movimentação inválida: colisão entre sondas");
            }
        });
    }
}
