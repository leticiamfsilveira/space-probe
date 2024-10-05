package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.InvalidMovementException;
import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.domain.*;
import org.springframework.stereotype.Service;


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
                    int newX = probe.getXPosition();
                    int newY = probe.getYPosition();

                    switch (probe.getOrientation()) {
                        case Probe.Orientation.NORTH -> newY += 1;
                        case Probe.Orientation.SOUTH -> newY -= 1;
                        case Probe.Orientation.EAST  -> newX += 1;
                        case Probe.Orientation.WEST  -> newX -= 1;
                    }

                    Planet planet = planets.findById(probe.getPlanetId()).orElseThrow(() -> new ResourceNotFoundException("Planeta não encontrado"));

                    if (isProbePositionValid(newX, newY, planet)) {
                        probe.moveForward(newX, newY);
                    }
                }
            }
        }

        probes.save(probe);
    }

    /**
     * Verifica se a nova posição da sonda é válida dentro do planeta, considerando os limites e a presença de outras sondas.
     *
     * Este método valida se a posição (x, y) está dentro dos limites do planeta e também verifica se há colisões
     * com outras sondas no mesmo planeta. Lança uma exceção caso a movimentação seja inválida.
     *
     * @param x a coordenada x da nova posição.
     * @param y a coordenada y da nova posição.
     * @param planet o planeta onde a sonda está se movendo.
     * @return true se a posição for válida e não houver colisões.
     * @throws InvalidMovementException se a posição estiver fora dos limites do planeta ou se houver colisão com outra sonda.
     */
    private boolean isProbePositionValid(int x, int y, Planet planet) {
        if (!(x >= 0 && x <= planet.getWidth() && y >= 0 && y <= planet.getHeight())) {
            throw new InvalidMovementException("Movimentação inválida: fora dos limites do planeta");
        }

        probes.findAll().forEach(probe -> {
            if (probe.getXPosition() == x && probe.getYPosition() == y && probe.getPlanetId() == planet.getId()) {
                throw new InvalidMovementException("Movimentação inválida: colisão entre sondas");
            }
        });

        return true;
    }
}
