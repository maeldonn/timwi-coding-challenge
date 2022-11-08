package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Tag createTag(@RequestBody TagRequest tagRequest) {
        Tag tag = Tag.builder()
                .name(tagRequest.getName())
                .albums(tagRequest.getAlbums())
                .build();
        return tagService.createTag(tag);
    }
}
