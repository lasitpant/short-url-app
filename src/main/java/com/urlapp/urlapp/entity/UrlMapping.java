package com.urlapp.urlapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "urlmapping")
public class UrlMapping {

    @Id
    @Column(nullable = false)
    String shortUrl;

    @Column(nullable = false)
    String longUrl;



    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }



    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

}
