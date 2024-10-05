package com.elo7.space_probe.domain;

import java.util.List;
import java.util.Optional;

public interface Probes {
    Probe save(Probe probe);

    Optional<Probe> findById(Integer id);

    List<Probe> findAll();
}
