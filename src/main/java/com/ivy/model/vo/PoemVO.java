package com.ivy.model.vo;

import com.ivy.model.BaseObject;

/**
 * Created by admin on 2017/11/20.
 */
public class PoemVO extends BaseObject{

    private Integer poemId;

    private String title;

    private String content;

    private String poetId;

    private String poetName;

    private String poemDynastyId;

    private String poemDynastyName;


    public Integer getPoemId() {
        return poemId;
    }

    public void setPoemId(Integer poemId) {
        this.poemId = poemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoetId() {
        return poetId;
    }

    public void setPoetId(String poetId) {
        this.poetId = poetId;
    }

    public String getPoetName() {
        return poetName;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public String getPoemDynastyName() {
        return poemDynastyName;
    }

    public void setPoemDynastyName(String poemDynastyName) {
        this.poemDynastyName = poemDynastyName;
    }

    public String getPoemDynastyId() {
        return poemDynastyId;
    }

    public void setPoemDynastyId(String poemDynastyId) {
        this.poemDynastyId = poemDynastyId;
    }
}
