package com.spotifychallenge.controllers.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotifychallenge.model.Album;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("albums")
    private List<Album> albums;
}
