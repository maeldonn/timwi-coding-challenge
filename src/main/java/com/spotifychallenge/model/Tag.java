package com.spotifychallenge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Tag Data Access Object
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "TAGS")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "TAG_ID")
    private Long tagId = null;

    @Column(name = "NAME", unique = true)
    private String name = null;

    @ManyToMany(mappedBy = "tags")
    List<Album> albums = null;
}
