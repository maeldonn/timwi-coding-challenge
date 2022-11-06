package com.spotifychallenge.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SpotifyAlbum {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String title = null;

    @JsonProperty("artists")
    private List<SpotifyArtist> artists = null;

    @JsonProperty("images")
    private List<SpotifyAlbumImage> images = null;

    @JsonProperty("release_date")
    private String releaseDate = null;

    @JsonProperty("release_date_precision")
    private String releaseDatePrecision = null;

    @JsonProperty("total_tracks")
    private Integer duration = null;

    @Data
    public static class SpotifyArtist {

        @JsonProperty("id")
        private String id = null;

        @JsonProperty("name")
        private String name = null;
    }

    @Data
    public static class SpotifyAlbumImage {

        @JsonProperty("url")
        private String url = null;

        @JsonProperty("height")
        private Integer height = null;

        @JsonProperty("width")
        private Integer width = null;
    }
}

