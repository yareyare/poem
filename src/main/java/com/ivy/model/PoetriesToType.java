package com.ivy.model;

import java.util.Date;

public class PoetriesToType {

    private Integer id;

    private Integer poetriesId;

    private Integer poetriesTypeId;

    private Date createdAt;

    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoetriesId() {
        return poetriesId;
    }

    public void setPoetriesId(Integer poetriesId) {
        this.poetriesId = poetriesId;
    }

    public Integer getPoetriesTypeId() {
        return poetriesTypeId;
    }

    public void setPoetriesTypeId(Integer poetriesTypeId) {
        this.poetriesTypeId = poetriesTypeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}