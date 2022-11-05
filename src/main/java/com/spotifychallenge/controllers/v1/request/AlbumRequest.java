package com.spotifychallenge.controllers.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class AlbumRequest {

    @JsonProperty("albumId")
    String albumId;

    @JsonProperty("title")
    String title;

    @JsonProperty("artist")
    String artist;

    @JsonProperty("thumbnail")
    String thumbnail;

    @JsonProperty("date")
    LocalDate date;

    @JsonProperty("duration")
    Integer duration;

    @JsonProperty("favorite")
    Boolean favorite;
}
