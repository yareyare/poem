package com.ivy.dao;

import com.ivy.model.po.Dynasty;

public interface DynastyMapper {

    int insert(Dynasty record);

    int insertSelective(Dynasty record);

    Dynasty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dynasty record);

    int updateByPrimaryKey(Dynasty record);
}