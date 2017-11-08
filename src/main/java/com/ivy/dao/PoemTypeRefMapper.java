package com.ivy.dao;

import com.ivy.model.po.PoemTypeRef;

public interface PoemTypeRefMapper {

    int insert(PoemTypeRef record);

    int insertSelective(PoemTypeRef record);

    PoemTypeRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoemTypeRef record);

    int updateByPrimaryKey(PoemTypeRef record);
}