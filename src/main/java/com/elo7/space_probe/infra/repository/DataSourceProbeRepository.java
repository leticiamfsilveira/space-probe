package com.elo7.space_probe.infra.repository;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataSourceProbeRepository implements Probes {
    private final SpringDataProbeRepository repository;

    public DataSourceProbeRepository(SpringDataProbeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Probe save(Probe probe) {
        return repository.save(probe);
    }

    @Override
    public Optional<Probe> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Probe> findAll() {
        return repository.findAll();
    }
}
