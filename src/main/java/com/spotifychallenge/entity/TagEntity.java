package com.spotifychallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TAG")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_GENERATOR")
    @SequenceGenerator(name = "TAG_GENERATOR", sequenceName = "SEQ_TAG_GENERATOR", allocationSize = 1)
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
