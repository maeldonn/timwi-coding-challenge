package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.AlbumRequest;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/search")
    public List<Album> getAlbums(@RequestParam String searchFilter) {
        return albumService.getAlbums(searchFilter);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add/{albumId}")
    public Album addAlbumToPersonalList(@PathVariable String albumId) {
        return albumService.addAlbumToPersonalList(albumId);
    }

    @DeleteMapping("/remove/{albumId}")
    public void removeAlbumFromList(@PathVariable String albumId) {
        albumService.removeAlbumFromPersonalList(albumId);
    }

    @PutMapping("/favorites/add")
    public Album addAlbumToFavorites(@RequestBody AlbumRequest albumRequest) {
        return albumService.addAlbumToFavorites(albumRequest.getAlbumId());
    }

    @PutMapping("/favorites/remove")
    public Album removeAlbumFromFavorites(@RequestBody AlbumRequest albumRequest) {
        return albumService.removeAlbumFromFavorites(albumRequest.getAlbumId());
    }
}
