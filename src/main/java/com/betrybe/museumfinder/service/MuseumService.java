package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  private final MuseumFakeDatabase database;

  /**
   * Instantiates a new Museum service.
   *
   * @param database the database
   */
  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> closestMuseum = database.getClosestMuseum(coordinate, maxDistance);

    if (closestMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return closestMuseum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (!CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }

    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> museum = database.getMuseum(id);
    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return museum.get();
  }
}
