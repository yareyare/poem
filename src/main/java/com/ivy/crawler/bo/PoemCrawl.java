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

    private List<PoemDetailCrawl> detailList = new ArrayList<PoemDetailCrawl>();

    public PoemCrawl() {
    }

    public PoemCrawl(String title, String chaodai, String zuozhe, String content, String tag) {
        this.title = title;
        this.chaodai = chaodai;
        this.zuozhe = zuozhe;
        this.content = content;
        this.tag = tag;
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
