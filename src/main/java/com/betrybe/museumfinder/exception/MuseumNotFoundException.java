package com.betrybe.museumfinder.exception;

/**
 * The type Museum not found exception.
 */
public class MuseumNotFoundException extends RuntimeException {
  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }
}
