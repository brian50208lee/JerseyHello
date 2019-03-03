package com.brian.jerseyhello.core.data;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Bean
 */
public class Message {
    private long id;
    private String author;
    private String content;
    private Date created;
    private Map<Long, Comment> comments;

    public Message() {
    }

    public Message(long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.created = new Date();
        this.comments = new HashMap<>();
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

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }
}
