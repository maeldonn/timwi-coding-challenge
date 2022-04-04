package com.spotifychallenge.repository;

import com.spotifychallenge.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for Tag
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
