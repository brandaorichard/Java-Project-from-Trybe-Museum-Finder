package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;

/**
 * A DTO for representing a museum.
 *
 * @param id The unique identifier of the museum.
 * @param name The name of the museum.
 * @param description A brief description of the museum.
 * @param address The address of the museum.
 * @param collectionType The type of collection the museum has.
 * @param subject The subject focus of the museum.
 * @param url The URL for more information about the museum.
 * @param coordinate The geographical coordinates of the museum.
 */
public record MuseumDto(
    Long id,
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    Coordinate coordinate
) {
}
