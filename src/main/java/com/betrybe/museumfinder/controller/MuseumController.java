package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private final MuseumServiceInterface service;

  /**
   * Instantiates a new Museum controller.
   *
   * @param service the service
   */
  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Create museum response entity.
   *
   * @param newMuseumDto the new museum dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto newMuseumDto) {
    Museum createdMuseum = service.createMuseum(ModelDtoConverter.dtoToModel(newMuseumDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMuseum);
  }

  /**
   * Gets closest museum.
   *
   * @param lat       the lat
   * @param lng       the lng
   * @param maxDistKm the max dist km
   * @return the closest museum
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam double lat,
      @RequestParam double lng,
      @RequestParam(name = "max_dist_km") double maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum closestMuseum = service.getClosestMuseum(coordinate, maxDistKm);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(closestMuseum);

    return ResponseEntity.status(HttpStatus.OK).body(museumDto);
  }

  /**
   * Gets museum by id.
   *
   * @param id the id
   * @return the museum by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseumById(@PathVariable Long id) {
    Museum museumId = service.getMuseum(id);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museumId);
    return ResponseEntity.status(HttpStatus.OK).body(museumDto);
  }
}

