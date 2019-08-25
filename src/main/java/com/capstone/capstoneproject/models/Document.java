package com.capstone.capstoneproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Please provide a title")
    @Size(min=6, message = "*Title must be at least 6 characters")
    private String title;

    @Column(name = "body")
    @NotEmpty(message = "Please enter information")
    @Size(min=6, message = "*Document is too short")
    private String body;


    public Document(){}

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
