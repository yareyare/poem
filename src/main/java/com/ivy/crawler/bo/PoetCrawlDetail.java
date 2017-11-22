package com.ivy.crawler.bo;

import com.ivy.model.BaseObject;

/**
 * Created by admin on 2017/11/3.
 */
public class PoetCrawlDetail extends BaseObject{

    private Integer id;     //诗人详情 id
    private Integer poetId; //诗人id
    private String type;    //详情类型
    private String content; //详情内容
    private String cankao;  //参考
    private Integer sort;  //显示顺序

    public PoetCrawlDetail() {
    }

    public PoetCrawlDetail(Integer id, Integer poetId, String type, String content, String cankao, Integer sort) {
        this.id = id;
        this.poetId = poetId;
        this.type = type;
        this.content = content;
        this.cankao = cankao;
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCankao() {
        return cankao;
    }

    public void setCankao(String cankao) {
        this.cankao = cankao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoetId() {
        return poetId;
    }

    public void setPoetId(Integer poetId) {
        this.poetId = poetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
