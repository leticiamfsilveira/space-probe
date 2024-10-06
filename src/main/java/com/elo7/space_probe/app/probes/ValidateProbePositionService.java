package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.InvalidMovementException;
import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Serviço responsável pela validação de posição das sondas dentro de um planeta.
 * Centraliza as regras de negócio para garantir que as sondas respeitem os limites dos planetas
 * e que não haja colisão entre sondas no mesmo espaço.
 */
@Service
public class ValidateProbePositionService {
    private final Probes probes;
    private final Planets planets;

    public ValidateProbePositionService(Probes probes, Planets planets) {
        this.probes = probes;
        this.planets = planets;
    }

    /**
     * Executa a validação de posição de uma sonda no planeta, verificando se a posição está
     * dentro dos limites e se não há outras sondas no mesmo local.
     * Caso alguma condição não seja atendida, lança uma exceção para indicar o erro.
     * @param x         Coordenada X da posição que a sonda deseja ocupar.
     * @param y         Coordenada Y da posição que a sonda deseja ocupar.
     *  @param planetId  ID do planeta no qual a sonda está se movimentando.
     */
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
