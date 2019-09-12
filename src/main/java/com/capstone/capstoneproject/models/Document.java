package com.capstone.capstoneproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Please provide a title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    public Document(){}

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

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
