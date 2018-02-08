package com.ivy.model.vo;

import com.ivy.model.BaseObject;

/**
 * Created by admin on 2017/11/21.
 */
public class TypeVO extends BaseObject {

    private Integer id;
    private String type;

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

}
