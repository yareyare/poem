package com.ivy.dao;

import com.ivy.model.po.PoemType;

public interface PoemTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoemType record);

    int insertSelective(PoemType record);

    PoemType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoemType record);

    int updateByPrimaryKey(PoemType record);
}