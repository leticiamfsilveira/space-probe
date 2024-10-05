package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProbeService {
    private final Probes probes;

    FindAllProbeService(Probes probes) {
        this.probes = probes;
    }

    public List<Probe> execute() {
        return probes.findAll();
    }

}
