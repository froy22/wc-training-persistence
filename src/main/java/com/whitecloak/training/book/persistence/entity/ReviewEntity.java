package com.whitecloak.training.book.persistence.entity;

import com.whitecloak.training.common.persistence.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "REVIEWS")
@Entity
public class ReviewEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn
    private BookEntity book;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
