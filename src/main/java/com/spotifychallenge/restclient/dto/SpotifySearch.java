package com.spotifychallenge.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpotifySearch {

    @JsonProperty("albums")
    private SpotifyAlbums albums = null;

    public List<SpotifyAlbum> getAlbums() {
        if (albums != null) {
            return albums.items;
        }
        return Collections.emptyList();
    }

    public static class SpotifyAlbums {

        @JsonProperty("items")
        private List<SpotifyAlbum> items = new ArrayList<>();
    }
}
