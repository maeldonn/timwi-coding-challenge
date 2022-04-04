package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.dto.mapper.album.TagMapper;
import com.spotifychallenge.dto.model.album.TagDto;
import com.spotifychallenge.service.album.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints for the tag ressource
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    // SERVICES
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Create a tag
     *
     * @param tagRequest the tag to create
     * @return The tag created
     */
    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagRequest tagRequest) {
        TagDto tagDto = tagService.createTag(TagMapper.toTagDto(tagRequest));
        return ResponseEntity.status(HttpStatus.OK).body(tagDto);
    }
}
