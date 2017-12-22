package com.ivy.crawler.bo;

import com.ivy.model.BaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/11/3.
 */
public class PoetCrawl extends BaseObject{

    private Integer id;
    private Integer refId; //爬下来的诗人的id
    private String name;
    private String introduce;
    private String picture; //base64
    private Integer dynastyId;
    private String poetUrl ;
    private List<PoetCrawlDetail> poetCrawlDetails = new ArrayList<>(); //poetUrl 详细介绍的url

    public PoetCrawl() {
    }

    public PoetCrawl(Integer id,Integer refId, String name, String introduce, String picture, Integer dynastyId, List<PoetCrawlDetail> poetCrawlDetails) {
        this.id = id;
        this.refId = refId;
        this.name = name;
        this.introduce = introduce;
        this.picture = picture;
        this.dynastyId = dynastyId;
        this.poetCrawlDetails = poetCrawlDetails ;
    }

    public String getPoetUrl() {
        return poetUrl;
    }

    public void setPoetUrl(String poetUrl) {
        this.poetUrl = poetUrl;
    }

    public List<PoetCrawlDetail> getPoetCrawlDetails() {
        return poetCrawlDetails;
    }

    public void setPoetCrawlDetails(List<PoetCrawlDetail> poetCrawlDetails) {
        this.poetCrawlDetails = poetCrawlDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
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
