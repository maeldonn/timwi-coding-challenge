package com.spotifychallenge.controllers.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotifychallenge.model.Album;
import lombok.Value;

import java.util.List;

@Value
public class TagRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("albums")
    private List<Album> albums;
}
