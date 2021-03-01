package com.urlapp.urlapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false)
    private Date createdOn;

    @Column(nullable = true)
    private Date expiresOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public long getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
