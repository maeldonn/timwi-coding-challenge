package com.spotifychallenge.dto.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * Spotify Album Data Transfer Object
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAlbumDto {

    @JsonProperty("id")
    private String albumId = null;

    @JsonProperty("name")
    private String title = null;

    @JsonProperty("artists")
    private List<SpotifyArtistDto> artists = null;

    @JsonProperty("images")
    private List<SpotifyAlbumImageDto> images = null;

    @JsonProperty("release_date")
    private Date date = null;

    @JsonProperty("total_tracks")
    private Integer duration = null;

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpotifyArtistDto {

        @JsonProperty("id")
        private String artistId = null;

        @JsonProperty("name")
        private String name = null;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpotifyAlbumImageDto {

        @JsonProperty("url")
        private String url = null;

        @JsonProperty("height")
        private String height = null;

        @JsonProperty("width")
        private String width = null;
    }
}