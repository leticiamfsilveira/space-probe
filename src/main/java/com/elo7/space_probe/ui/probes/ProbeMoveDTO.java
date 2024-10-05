package com.elo7.space_probe.ui.probes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProbeMoveDTO(
        @JsonProperty("commands")
        @NotNull(message = "Probe move commands can't be null")
        String commands
) { }
