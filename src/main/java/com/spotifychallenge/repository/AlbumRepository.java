package com.spotifychallenge.repository;

import com.spotifychallenge.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {

    @Modifying
    @Query(value = "UPDATE ALBUMS SET FAVORITE = ?1 WHERE ID = ?2", nativeQuery = true)
    void setAlbumFavoriteById(Boolean favorite, String id);
}
