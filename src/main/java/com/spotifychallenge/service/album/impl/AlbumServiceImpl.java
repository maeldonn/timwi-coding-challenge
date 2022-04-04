package com.spotifychallenge.service.album.impl;

import com.spotifychallenge.dto.mapper.album.AlbumMapper;
import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.repository.TagRepository;
import com.spotifychallenge.service.album.AlbumService;
import com.spotifychallenge.util.SpotifyRestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link AlbumService}
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    private static final Logger logger = LogManager.getLogger(AlbumServiceImpl.class);

    //SERVICES
    private final SpotifyRestClient spotifyRestClient;
    private final AlbumRepository albumRepository;
    private final TagRepository tagRepository;

    /**
     * {@inheritDoc}
     */
    public AlbumServiceImpl(SpotifyRestClient spotifyRestClient, AlbumRepository albumRepository, TagRepository tagRepository) {
        this.spotifyRestClient = spotifyRestClient;
        this.albumRepository = albumRepository;
        this.tagRepository = tagRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
//    @Cacheable(value = "Album", condition = "#personal")
    public List<AlbumDto> getAlbums(String searchQuery, Boolean personal) {

        List<AlbumDto> albums = new ArrayList<>();

        // If a search query is specified, then we search the album into spotify api
        if (searchQuery != null && !searchQuery.isEmpty()) {
            albums = spotifyRestClient.searchAlbums(searchQuery)
                    .stream()
                    .map(albumDto -> {

                        // Search the album in personal list
                        Optional<Album> album = albumRepository.findById(albumDto.getAlbumId());

                        // Set personal, favorite and tags
                        album.ifPresentOrElse(personalAlbum -> albumDto
                                .setPersonal(true)
                                .setFavorite(personalAlbum.getFavorite())
                                .setTags(AlbumMapper.toAlbumDtoWithTags(personalAlbum).getTags()), () -> albumDto.setPersonal(false).setFavorite(false));

                        return albumDto;
                    })
                    .collect(Collectors.toList());
        }

        // If the list of albums is empty then we get the personal albums
        // Else we filter the actual liste to get only the favorite albums
        if (Boolean.TRUE.equals(personal)) {
            if (albums.isEmpty()) {
                albums = albumRepository.findAll().stream()
                        .map(AlbumMapper::toAlbumDtoWithTags)
                        .collect(Collectors.toList());
            } else {
                albums = albums.stream()
                        .filter(AlbumDto::getPersonal)
                        .collect(Collectors.toList());
            }
        }

        return albums;
    }

    /**
     * {@inheritDoc}
     */
    @Override
//    @CachePut(value = "Album")
    public AlbumDto addAlbumToPersonalList(String albumId) {

        // Search if id is valid
        AlbumDto albumDto = spotifyRestClient.getAlbum(albumId);

        // ID doesnt exist
        if (albumDto == null) {
            return null;
        }

        // If it is a valid id, add album to personal list
        Album album = albumRepository.saveAndFlush(AlbumMapper.toAlbum(albumDto));

        logger.info("Album added to personal list");

        return AlbumMapper.toAlbumDto(album);
    }

    @Override
//    @CacheEvict(value = "Album")
    public void removeAlbumFromPersonalList(String albumId) {
        albumRepository.findById(albumId).ifPresent(album -> {

            // If it's the only album of the tag
            // Add the tag to the remove list
            List<Tag> tagToRemove = album.getTags().stream()
                    .filter(tag -> tag.getAlbums().size() == 1)
                    .collect(Collectors.toList());

            albumRepository.delete(album);
            tagRepository.deleteAll(tagToRemove);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CachePut(value = "Album")
    public AlbumDto updateAlbumFavorite(String albumId, Boolean favorite) {

        Optional<Album> album = albumRepository.findById(albumId);

        // Update and return the entity
        return album.map(value -> AlbumMapper.toAlbumDto(albumRepository.save(value.setFavorite(favorite)))).orElse(null);
    }
}
