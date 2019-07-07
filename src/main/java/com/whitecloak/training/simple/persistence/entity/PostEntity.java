package com.whitecloak.training.simple.persistence.entity;

import com.whitecloak.training.common.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "POST")
@Entity
public class PostEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

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
}
