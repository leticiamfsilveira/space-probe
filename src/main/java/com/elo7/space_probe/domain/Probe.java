package com.elo7.space_probe.domain;

import jakarta.persistence.*;


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

    @Column(name = "orientation", nullable = false)
    private Orientation orientation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", referencedColumnName = "id", nullable = false)
    private Planet planet;

    @Deprecated // hibernate only
    public Probe() {}

    /**
     * Construtor para inicializar a posição sem orientação (exemplo inicial)
     */
    public Probe(String name, Integer x, Integer y, Planet planet) {
        this.name = name;
        this.position = new Position(x, y);
        this.orientation = Orientation.NORTH; // Orientação padrão como Norte.
        this.planet = planet;
    }

    public Probe(String name, Integer x, Integer y, String orientation, Planet planet) {
        this.name = name;
        this.position = new Position(x, y);
        this.orientation = Orientation.valueOf(orientation);
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

    public Orientation getOrientation() { return orientation; }

    /**
     * Métodos para girar a sonda à esquerda ou direita.
     */
    public void turnLeft() {
        switch (this.orientation) {
            case NORTH -> this.orientation = Orientation.WEST;
            case WEST -> this.orientation = Orientation.SOUTH;
            case SOUTH -> this.orientation = Orientation.EAST;
            case EAST -> this.orientation = Orientation.NORTH;
        }
    }

    public void turnRight() {
        switch (this.orientation) {
            case NORTH -> this.orientation = Orientation.EAST;
            case EAST -> this.orientation = Orientation.SOUTH;
            case SOUTH -> this.orientation = Orientation.WEST;
            case WEST -> this.orientation = Orientation.NORTH;
        }
    }

    /**
     * Move a sonda para as novas coordenadas.
     */
    public void moveForward(Integer x, Integer y) {
        this.position = new Position(x, y);
    }

    /**
     * Enum para representar as orientações possíveis da sonda.
     */
    public enum Orientation {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}
