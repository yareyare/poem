package com.ivy.model.po;

public class Translation {
    private Integer id;

    private String content;

    private Integer poetriesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPoetriesId() {
        return poetriesId;
    }

    public void setPoetriesId(Integer poetriesId) {
        this.poetriesId = poetriesId;
    }
}