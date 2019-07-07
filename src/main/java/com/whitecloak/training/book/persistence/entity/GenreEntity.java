package com.whitecloak.training.book.persistence.entity;

import com.whitecloak.training.common.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "GENRES")
@Entity
public class GenreEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<BookEntity> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
