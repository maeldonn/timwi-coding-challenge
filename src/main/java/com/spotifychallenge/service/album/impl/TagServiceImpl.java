package com.spotifychallenge.service.album.impl;

import com.spotifychallenge.dto.mapper.album.TagMapper;
import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.dto.model.album.TagDto;
import com.spotifychallenge.model.Tag;
import com.spotifychallenge.repository.AlbumRepository;
import com.spotifychallenge.repository.TagRepository;
import com.spotifychallenge.service.album.TagService;
import org.springframework.stereotype.Service;

/**
 * Implementation of TagService
 */
@Service
public class TagServiceImpl implements TagService {

    //SERVICES
    private final TagRepository tagRepository;
    private final AlbumRepository albumRepository;

    public TagServiceImpl(TagRepository tagRepository, AlbumRepository albumRepository) {
        this.tagRepository = tagRepository;
        this.albumRepository = albumRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagDto createTag(TagDto tagDto) {

        // Create the tag
        Tag tag = tagRepository.save(TagMapper.toTag(tagDto));

        // Create the link for each album
        for (AlbumDto albumDto : tagDto.getAlbums()) {
            albumRepository.findById(albumDto.getAlbumId()).ifPresent(album -> {
                album.getTags().add(tag);
                albumRepository.save(album);
            });
        }

        return TagMapper.toTagDto(tag);
    }
}
