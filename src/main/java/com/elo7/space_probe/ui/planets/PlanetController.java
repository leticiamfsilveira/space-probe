package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.app.planets.CreatePlanetService;
import com.elo7.space_probe.app.planets.FindAllPlanetService;
import com.elo7.space_probe.app.planets.FindPlanetService;
import com.elo7.space_probe.domain.Planet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/planets")
class PlanetController {

    private final CreatePlanetService createPlanetService;
    private final FindPlanetService findPlanetService;
    private final FindAllPlanetService findAllPlanetService;
    private final PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter;
    private final PlanetToDtoConverter planetToDtoConverter;

    PlanetController(CreatePlanetService createPlanetService, FindPlanetService findPlanetService, FindAllPlanetService findAllPlanetService, PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter, PlanetToDtoConverter planetToDtoConverter) {
        this.createPlanetService = createPlanetService;
        this.findPlanetService = findPlanetService;
        this.findAllPlanetService = findAllPlanetService;
        this.planetCreateDTOToModelConverter = planetCreateDTOToModelConverter;
        this.planetToDtoConverter = planetToDtoConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<PlanetDTO> findAll() {
        List<Planet> probes = findAllPlanetService.execute();
        return probes.stream().map(planetToDtoConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    PlanetDTO findById(@PathVariable("id") Integer id) {
        Optional<Planet> probe = findPlanetService.execute(id);
        return probe.map(planetToDtoConverter::convert).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    PlanetDTO create(@RequestBody PlanetCreateDTO probeCreateDTO) {
        Planet probe = planetCreateDTOToModelConverter.convert(probeCreateDTO);
        Planet createdProbe = createPlanetService.execute(probe);
        return planetToDtoConverter.convert(createdProbe);
    }
}
