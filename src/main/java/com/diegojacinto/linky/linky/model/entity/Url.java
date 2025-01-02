package com.diegojacinto.linky.linky.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortCode;
    private boolean is_anonymous;

    public Url(String originalUrl, String shortCode, boolean is_anonymous) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.is_anonymous = is_anonymous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

}
