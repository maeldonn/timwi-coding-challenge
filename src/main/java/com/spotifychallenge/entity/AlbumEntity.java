package com.spotifychallenge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ALBUM")
public class AlbumEntity {

    @Id
    @Column(name = "ID")
    private String id = null;

    @Column(name = "TITLE")
    private String title = null;

    @Column(name = "ARTISTS")
    private String artists = null;

    @Column(name = "IMAGE")
    private String image = null;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate = null;

    @Column(name = "DURATION")
    private Integer duration = null;

    @Column(name = "FAVORITE")
    private Boolean favorite = null;

    @ManyToMany
    @JoinTable(
            name = "ALBUM_TAGS",
            joinColumns = @JoinColumn(name = "ALBUM_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    List<TagEntity> tags = new ArrayList<>();
}
