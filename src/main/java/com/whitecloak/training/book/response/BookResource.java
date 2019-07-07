package com.whitecloak.training.book.response;

import java.util.HashSet;
import java.util.Set;

public class BookResource {

    private Long id;
    private String title;
    private String description;
    private Set<GenreResource> genres = new HashSet<>();
    private Set<ReviewResource> reviews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<GenreResource> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreResource> genres) {
        this.genres = genres;
    }

    public Set<ReviewResource> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewResource> reviews) {
        this.reviews = reviews;
    }
}
