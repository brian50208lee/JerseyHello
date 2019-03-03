package com.brian.jerseyhello.core.data;

import java.util.Date;

public class Profile {
    private long id;
    private String name;
    private Date created;

    public Profile() {
    }

    public Profile(long id, String name) {
        this.id = id;
        this.name = name;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
