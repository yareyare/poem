package com.ivy.model.po;

import com.ivy.model.BasePo;

import java.util.Date;

public class Session extends BasePo {

    private String id;

    private Integer userId;

    private Date invalidtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId == null ? null : userId;
    }

    public Date getInvalidtime() {
        return invalidtime;
    }

    public void setInvalidtime(Date invalidtime) {
        this.invalidtime = invalidtime;
    }

}