package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MoveProbeService {
    private final Probes probes;
    private final ValidateProbePositionService validateProbePositionService;

    public MoveProbeService(Probes probes, ValidateProbePositionService validateProbePositionService) {
        this.probes = probes;
        this.validateProbePositionService = validateProbePositionService;
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
                    int newX = probe.getXPosition();
                    int newY = probe.getYPosition();

                    switch (probe.getOrientation()) {
                        case Probe.Orientation.NORTH -> newY += 1;
                        case Probe.Orientation.SOUTH -> newY -= 1;
                        case Probe.Orientation.EAST  -> newX += 1;
                        case Probe.Orientation.WEST  -> newX -= 1;
                    }

                    validateProbePositionService.execute(newX, newY, probe.getPlanetId());
                    probe.moveForward(newX, newY);
                }
            }
        }

        probes.save(probe);
    }
}
