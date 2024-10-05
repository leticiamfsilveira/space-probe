package com.elo7.space_probe.ui.probes;

import com.elo7.space_probe.domain.Probe;
import org.springframework.stereotype.Component;

@Component
class ProbeToDtoConverter {
    ProbeDTO convert(Probe probe) {
        return new ProbeDTO(probe.getId(), probe.getName(), probe.getXPosition(), probe.getYPosition(), probe.getPlanetId());
    }
}
