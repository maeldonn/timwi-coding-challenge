package com.spotifychallenge.controllers.v1.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.dto.TagDto;
import com.spotifychallenge.service.TagService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public TagDto createTag(@RequestBody TagRequest tagRequest) {
        TagDto tag = TagDto.builder()
                .name(tagRequest.getName())
                .albums(tagRequest.getAlbums())
                .build();
        return tagService.createTag(tag);
    }
}
