package com.capstone.capstoneproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Doc {

    @GeneratedValue
    @Id
    private int id;

    @Column(unique = true)
    @NotNull
    @Size(min=3)
    private String title;

    @Column
    @NotNull
    @Size(min=3)
    private String body;

    public Doc(){}

    public Doc(Integer id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }
}
