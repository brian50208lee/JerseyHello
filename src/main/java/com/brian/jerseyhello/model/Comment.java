package com.brian.jerseyhello.model;

import java.util.Date;

public class Comment {
    private long id;
    private String author;
    private String comment;
    private Date created;

    public Comment() {
    }

    public Comment(long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
