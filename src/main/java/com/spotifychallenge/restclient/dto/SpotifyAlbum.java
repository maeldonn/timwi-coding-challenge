package com.spotifychallenge.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpotifyAlbum {

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

    @Data
    public static class SpotifyArtistDto {

        @JsonProperty("id")
        private String artistId = null;

        @JsonProperty("name")
        private String name = null;
    }

    @Data
    public static class SpotifyAlbumImageDto {

        @JsonProperty("url")
        private String url = null;

        @JsonProperty("height")
        private String height = null;

        @JsonProperty("width")
        private String width = null;
    }
}

