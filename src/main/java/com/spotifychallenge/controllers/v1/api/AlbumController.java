package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.AlbumRequest;
import com.spotifychallenge.dto.mapper.AlbumMapper;
import com.spotifychallenge.dto.model.AlbumDto;
import com.spotifychallenge.dto.response.Response;
import com.spotifychallenge.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/search")
    public Response getAlbums(@RequestParam String searchFilter) throws IOException {
        List<AlbumDto> albums = albumService.getAlbums(searchFilter);
        return Response.ok().setPayload(albums);
    }

    @PostMapping("/add/{albumId}")
    public Response addAlbumToPersonalList(@PathVariable String albumId) {
        AlbumDto albumDto = albumService.addAlbumToPersonalList(albumId);

        // If no album, then the parameter is invalid
        if (albumDto != null) {
            Response.badRequest();
        }

        return Response.created().setPayload(albumDto);
    }

    @DeleteMapping("/remove/{albumId}")
    public Response removeAlbumFromList(@PathVariable String albumId) {
        albumService.removeAlbumFromPersonalList(albumId);
        return Response.ok();
    }

    @PutMapping("/favorites/add")
    public Response addAlbumToFavorites(@RequestBody AlbumRequest albumRequest) {
        AlbumDto albumDto = albumService.addAlbumToFavorites(AlbumMapper.toAlbumDto(albumRequest));

        // If no album, then the parameter is invalid
        if (albumDto != null) {
            Response.badRequest();
        }

        return Response.ok().setPayload(albumDto);
    }

    @PutMapping("/favorites/remove")
    public Response removeAlbumFromFavorites(@RequestBody AlbumRequest albumRequest) {
        AlbumDto albumDto = albumService.removeAlbumFromFavorites(AlbumMapper.toAlbumDto(albumRequest));

        // If no album, then the album is not in personal list
        if (albumDto != null) {
            Response.notFound();
        }

        return Response.ok().setPayload(albumDto);
    }
}
