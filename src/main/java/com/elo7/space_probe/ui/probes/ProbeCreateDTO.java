package com.elo7.space_probe.ui.probes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProbeCreateDTO(
        @JsonProperty("name")
        @NotNull(message = "Probe name can't be null")
        String name,
        @JsonProperty("x")
        @NotNull(message = "Probe x can't be null")
        Integer x,
        @JsonProperty("y")
        @NotNull(message = "Probe y can't be null")
        Integer y,
        @JsonProperty("planet_id")
        @NotNull(message = "Probe planet_id can't be null")
        Integer planetId
) { }
