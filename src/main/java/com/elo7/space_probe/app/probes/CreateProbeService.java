package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

@Service
public class CreateProbeService {
    private final Probes probes;

    CreateProbeService(Probes probes) {
        this.probes = probes;
    }

    public Probe execute(Probe probe) {
        return probes.save(probe);
    }

}
