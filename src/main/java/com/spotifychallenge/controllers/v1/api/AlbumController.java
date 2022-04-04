package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.controllers.v1.request.AlbumRequest;
import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.service.album.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST endpoints for the album ressource
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    // SERVICES
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Get all albums
     *
     * @param searchQuery The search query
     * @param personal Personal filter
     * @return a list of albums
     */
    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAlbums(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = false) Boolean personal
    ) {
        return ResponseEntity.ok(albumService.getAlbums(searchQuery, personal));
    }

    /**
     * Add an album to personal list
     *
     * @param albumId The album id
     * @return The album if the id is valid
     */
    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumDto> addAlbumToPersonalList(@PathVariable String albumId) {
        AlbumDto albumDto = albumService.addAlbumToPersonalList(albumId);

        // If album is null, then the parameter is invalid
        return ResponseEntity
                .status(albumDto == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED)
                .body(albumDto);
    }

    /**
     * Remove an album from personal list
     *
     * @param albumId The album id
     * @return OK
     */
    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> removeAlbumFromPersonalList(@PathVariable String albumId) {
        albumService.removeAlbumFromPersonalList(albumId);
        return ResponseEntity.ok().build();
    }

    /**
     * Update album to favorite
     *
     * @param albumRequest The album to update
     * @return The updated album if albumRequest is valid
     */
    @PatchMapping("/{albumId}")
    public ResponseEntity<AlbumDto> updateAlbumFavorite(@PathVariable String albumId, @RequestBody AlbumRequest albumRequest) {
        AlbumDto albumDto = albumService.updateAlbumFavorite(albumId, albumRequest.getFavorite());

        return ResponseEntity
                .status(albumDto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
                .body(albumDto);
    }
}
