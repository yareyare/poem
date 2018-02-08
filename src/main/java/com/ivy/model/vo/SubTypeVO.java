package com.ivy.model.vo;

import com.ivy.model.BaseObject;

/**
 * Created by admin on 2017/11/21.
 */
public class SubTypeVO extends BaseObject{

    private Integer id;
    private String type;
    private String type1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
