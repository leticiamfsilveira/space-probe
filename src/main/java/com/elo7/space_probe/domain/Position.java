package com.elo7.space_probe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class Position {
    @Column(name = "x", nullable = false)
    private Integer x;

    @Column(name = "y", nullable = false)
    private Integer y;

    @Deprecated // hibernate only
    public Position() {}

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
