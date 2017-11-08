package com.ivy.dao;

import com.ivy.model.po.PoemType;

public interface PoemTypeMapper {

    int insert(PoemType record);

    int insertSelective(PoemType record);

    PoemType selectByPrimaryKey(Integer id);

    PoemType selectByTypeAndType1(String type,String type1);

    int updateByPrimaryKeySelective(PoemType record);

    int updateByPrimaryKey(PoemType record);
}