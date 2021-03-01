package com.urlapp.urlapp.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name="keystore")
public class KeyStore {

    @Id
    private String keyUrl;

    @Column(nullable = false)
    private boolean activeState;

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    public String getKey() {
        return keyUrl;
    }

    public void setKey(String keyUrl) {
        this.keyUrl = keyUrl;
    }

    public boolean isActiveState() {
        return activeState;
    }
}
