package com.whitecloak.training.book.persistence.entity;

import com.whitecloak.training.common.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Table(name = "BOOKS")
@Entity
public class BookEntity extends BaseEntity {

    @Column
    private String title;

    @Lob
    @Column
    private String description;

    @ManyToMany
    private Set<GenreEntity> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }
}
