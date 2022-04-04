package com.spotifychallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 * Tag Data Access Object
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "TAGS")
public class Tag {

    // PRIMARY KEY
    @Id
    @GeneratedValue
    @Column(name = "TAG_ID", nullable = false)
    private Long tagId = null;

    // COLUMNS
    @Column(name = "NAME", nullable = false)
    private String name = null;

    // FOREIGN KEYS
    @ManyToMany(mappedBy = "tags")
    Set<Album> albums = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tag tag = (Tag) o;
        return tagId != null && Objects.equals(tagId, tag.tagId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
