package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.TagRequest;
import com.spotifychallenge.dto.mapper.TagMapper;
import com.spotifychallenge.dto.model.TagDto;
import com.spotifychallenge.dto.response.Response;
import com.spotifychallenge.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Create a new tag
     *
     * @param tagRequest the tag to create
     * @return The tag created
     */
    @PostMapping("/add")
    public Response createTag(@RequestBody TagRequest tagRequest) {
        TagDto tagDto = tagService.createTag(TagMapper.toTagDto(tagRequest));
        return Response.ok().setPayload(tagDto);
    }
}
