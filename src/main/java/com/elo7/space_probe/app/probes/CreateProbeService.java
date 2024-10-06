package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

@Service
public class CreateProbeService {
    private final Probes probes;
    private final ValidateProbePositionService validateProbePositionService;

    CreateProbeService(Probes probes, ValidateProbePositionService validateProbePositionService) {
        this.probes = probes;
        this.validateProbePositionService = validateProbePositionService;
    }

    public Probe execute(Probe probe) {
        validateProbePositionService.execute(probe.getXPosition(), probe.getYPosition(), probe.getPlanetId());
        return probes.save(probe);
    }

}
