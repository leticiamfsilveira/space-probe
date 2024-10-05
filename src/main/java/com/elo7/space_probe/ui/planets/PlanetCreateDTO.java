package com.elo7.space_probe.ui.planets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record PlanetCreateDTO(
        @JsonProperty("name")
        @NotNull(message = "Planet name can't be null")
        String name,
        @JsonProperty("width")
        @NotNull(message = "Planet width can't be null")
        Integer width,
        @JsonProperty("height")
        @NotNull(message = "Planet height can't be null")
        Integer height
) { }
