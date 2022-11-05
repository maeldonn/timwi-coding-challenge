package com.spotifychallenge.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Tag {

    private Long id;

    private String name;

    private List<Album> albums;
}
