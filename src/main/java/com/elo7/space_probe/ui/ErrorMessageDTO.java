package com.elo7.space_probe.ui;

import org.springframework.http.HttpStatus;

public record ErrorMessageDTO(String message, HttpStatus status) { }
