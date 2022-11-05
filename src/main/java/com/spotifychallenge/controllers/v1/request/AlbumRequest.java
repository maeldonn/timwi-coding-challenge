package com.spotifychallenge.controllers.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumRequest {

    @JsonProperty("albumId")
    private String albumId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("favorite")
    private Boolean favorite;
}
