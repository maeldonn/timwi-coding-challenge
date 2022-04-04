package com.spotifychallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Album Data Access Object
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "ALBUMS")
public class Album {

    // PRIMARY KEY
    @Id
    @Column(name = "ALBUM_ID", nullable = false)
    private String albumId = null;

    // COLUMNS
    @Column(name = "TITLE", nullable = false)
    private String title = null;

    @Column(name = "ARTIST", nullable = false)
    private String artist = null;

    @Column(name = "IMAGE", nullable = false)
    private String image = null;

    @Column(name = "DATE", nullable = false)
    private Date date = null;

    @Column(name = "DURATION", nullable = false)
    private Integer duration = null;

    @Column(name = "FAVORITE", nullable = false)
    private Boolean favorite = null;

    // FOREIGN KEYS
    @ManyToMany
    @JoinTable(name = "ALBUM_TAGS", joinColumns = @JoinColumn(name = "ALBUM_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    Set<Tag> tags = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Album album = (Album) o;
        return albumId != null && Objects.equals(albumId, album.albumId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
