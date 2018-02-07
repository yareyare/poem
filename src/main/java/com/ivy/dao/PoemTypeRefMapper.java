package com.ivy.dao;

import com.ivy.model.po.PoemTypeRef;

import java.util.Map;

public interface PoemTypeRefMapper {

    int insert(PoemTypeRef record);

    int insertSelective(PoemTypeRef record);

    PoemTypeRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoemTypeRef record);

    int updateByPrimaryKey(PoemTypeRef record);

    PoemTypeRef selectByParam(Map<String,Integer> param);
}