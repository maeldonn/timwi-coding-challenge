package com.spotifychallenge.repository;

import com.spotifychallenge.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Album
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {
}
