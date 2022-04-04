package com.spotifychallenge.dto.model.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Spotify API Reponse
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifySearchDto {

    @JsonProperty("albums")
    private SpotifyAlbumsDto albums = null;

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpotifyAlbumsDto {

        @JsonProperty("items")
        private List<SpotifyAlbumDto> items = null;
    }
}