package com.spotifychallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ALBUMS")
public class Album {

    @Id
    @Column(name = "ALBUM_ID")
    private String albumId = null;

    @Column(name = "TITLE")
    private String title = null;

    @Column(name = "DATE")
    private Date date = null;

    @Column(name = "DURATION")
    private Integer duration = null;

    @Column(name = "FAVORITE")
    private Boolean favorite = null;

    @ManyToMany
    @JoinTable(
        name = "ALBUM_TAGS",
        joinColumns = @JoinColumn(name = "ALBUM_ID"),
        inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    List<Tag> tags = null;
}
