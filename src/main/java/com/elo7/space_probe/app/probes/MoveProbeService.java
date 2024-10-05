package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MoveProbeService {
    private final Probes probes;
    private final Planets planets;

    MoveProbeService(Probes probes, Planets planets) {
        this.probes = probes;
        this.planets = planets;
    }

    /**
     * Executa uma sequência de comandos na sonda, movendo-a ou girando-a.
     * @param probe A sonda que receberá os comandos.
     * @param commands A string de comandos, onde:
     *                 - 'L' representa um giro para a esquerda.
     *                 - 'R' representa um giro para a direita.
     *                 - 'M' representa um movimento para frente.
     */
    public void execute(Probe probe, String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                case 'M' -> {
                    probe.moveForward(planets.findById(probe.getPlanetId()).get());
                }
            }
        }

        probes.save(probe);
    }
}
