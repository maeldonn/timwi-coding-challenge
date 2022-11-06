package com.spotifychallenge.controllers.v1.request;

import com.spotifychallenge.model.Album;
import lombok.Value;

import java.util.List;

@Value
public class TagRequest {

    String name;

    List<Album> albums;
}
