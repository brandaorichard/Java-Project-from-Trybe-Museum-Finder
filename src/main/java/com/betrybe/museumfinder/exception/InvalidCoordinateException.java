package com.betrybe.museumfinder.exception;

/**
 * Exception thrown when coordinates are invalid.
 */
public class InvalidCoordinateException extends RuntimeException {
  public InvalidCoordinateException(String message) {
    super(message);
  }
}