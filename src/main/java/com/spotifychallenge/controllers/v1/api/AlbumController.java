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

    @GetMapping
    public List<Album> getAlbums(@RequestParam String searchFilter) {
        return albumService.getAlbums(searchFilter);
    }

    @GetMapping("/personal")
    public List<Album> getPersonalAlbums() {
        return albumService.getPersonalAlbums();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Album addAlbumToPersonalList(@PathVariable String id) {
        return albumService.addAlbumToPersonalList(id);
    }

    @DeleteMapping("/{id}")
    public void removeAlbumFromList(@PathVariable String id) {
        albumService.removeAlbumFromPersonalList(id);
    }

    @PutMapping
    public Album toggleFavoriteAlbum(@RequestBody AlbumRequest albumRequest) {
        return albumService.toggleFavoriteAlbum(albumRequest.getId());
    }
}
