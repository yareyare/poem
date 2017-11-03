package com.ivy.crawler.bo;

import com.ivy.core.model.BaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/11/1.
 */

public class PoemCrawl extends BaseObject{

    private String title;    //标题
    private String chaodai;  //朝代
    private String zuozhe;   //作者
    private String content;  //正文
    private String tag;      //类别
    private String type;     //大分类
    private String type1;    //小分类
    private PoetCrawl poetCrawl; //诗人简介

    private List<PoemDetailCrawl> detailList = new ArrayList<PoemDetailCrawl>();

    public PoemCrawl() {
    }

    public PoemCrawl(String title, String chaodai, String zuozhe, String content, String tag, String type, String type1, PoetCrawl poetCrawl, List<PoemDetailCrawl> detailList) {
        this.title = title;
        this.chaodai = chaodai;
        this.zuozhe = zuozhe;
        this.content = content;
        this.tag = tag;
        this.type = type;
        this.type1 = type1;
        this.poetCrawl = poetCrawl;
        this.detailList = detailList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public PoetCrawl getPoetCrawl() {
        return poetCrawl;
    }

    public void setPoetCrawl(PoetCrawl poetCrawl) {
        this.poetCrawl = poetCrawl;
    }

    public List<PoemDetailCrawl> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PoemDetailCrawl> detailList) {
        this.detailList = detailList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChaodai() {
        return chaodai;
    }

    public void setChaodai(String chaodai) {
        this.chaodai = chaodai;
    }

    public String getZuozhe() {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
