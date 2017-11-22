package com.ivy.dao;

import com.ivy.model.po.PoemType;

import java.util.Map;

public interface PoemTypeMapper {

    int insert(PoemType record);

    int insertSelective(PoemType record);

    PoemType selectByPrimaryKey(Integer id);

    PoemType selectByTypeAndType1(Map<String,String> param);

    int updateByPrimaryKeySelective(PoemType record);

    int updateByPrimaryKey(PoemType record);
}