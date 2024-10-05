package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoveProbeService {
    private final Probes probes;

    MoveProbeService(Probes probes) {
        this.probes = probes;
    }

    public void execute(Optional<Probe> probe, String commands) {

    }
}
