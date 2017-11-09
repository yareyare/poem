package com.ivy.crawler.bo;

import com.ivy.model.BaseObject;

/**
 * Created by admin on 2017/11/1.
 */
public class PoemDetailCrawl extends BaseObject{

    private Integer index;
    private String type;
    private String detail;
    private String cankao;

    public PoemDetailCrawl() {
    }

    public PoemDetailCrawl(Integer index, String type, String detail, String cankao) {
        this.index = index;
        this.type = type;
        this.detail = detail;
        this.cankao = cankao;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCankao() {
        return cankao;
    }

    public void setCankao(String cankao) {
        this.cankao = cankao;
    }
}
