package com.spotifychallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TAG")
public class TagEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id = null;

    @Column(name = "NAME", unique = true)
    private String name = null;

    @ManyToMany(mappedBy = "tags")
    List<AlbumEntity> albums = new ArrayList<>();

    public boolean isMappedToOneAlbum() {
        return albums.size() == 1;
    }
}
