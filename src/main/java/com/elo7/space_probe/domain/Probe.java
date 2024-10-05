package com.elo7.space_probe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "module")
public class Probe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", referencedColumnName = "id", nullable = false)
    private Planet planet;

    @Deprecated // hibernate only
    public Probe() {}

    public Probe(String name, Integer x, Integer y, Planet planet) {
        this.name = name;
        this.position = new Position(x, y);
        this.planet = planet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getXPosition() {
        return position.getX();
    }

    public Integer getYPosition() {
        return position.getY();
    }

    public Integer getPlanetId() {
        return planet.getId();
    }
}
