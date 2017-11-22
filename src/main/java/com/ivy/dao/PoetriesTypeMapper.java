package com.ivy.dao;

import com.ivy.model.po.PoetriesType;

public interface PoetriesTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoetriesType record);

    int insertSelective(PoetriesType record);

    PoetriesType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoetriesType record);

    int updateByPrimaryKey(PoetriesType record);
}