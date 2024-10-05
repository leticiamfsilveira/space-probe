package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProbeService {
    private final Probes probes;

    FindProbeService(Probes probes) {
        this.probes = probes;
    }

    public Optional<Probe> execute(Integer id) {
        return probes.findById(id);
    }

}
