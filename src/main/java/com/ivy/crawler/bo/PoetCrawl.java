package com.ivy.crawler.bo;

/**
 * Created by admin on 2017/11/3.
 */
public class PoetCrawl {

    private Integer id;
    private String name;
    private String introduce;
    private String picture; //base64
    private Integer dynastyId;
    private String poetUrl; //poetUrl 详细介绍的url

    public PoetCrawl() {
    }

    public PoetCrawl(Integer id, String name, String introduce, String picture, Integer dynastyId, String poetUrl) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.picture = picture;
        this.dynastyId = dynastyId;
        this.poetUrl = poetUrl;
    }

    public String getPoetUrl() {
        return poetUrl;
    }

    public void setPoetUrl(String poetUrl) {
        this.poetUrl = poetUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getDynastyId() {
        return dynastyId;
    }

    public void setDynastyId(Integer dynastyId) {
        this.dynastyId = dynastyId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
