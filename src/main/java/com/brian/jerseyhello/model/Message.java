package com.brian.jerseyhello.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Java Bean
 */
public class Message {
    private long id;
    private String author;
    private String content;
    private Date created;

    public Message() {
    }

    public Message(long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
