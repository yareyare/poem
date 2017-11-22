package com.ivy.dao;

import com.ivy.model.po.PoetriesToType;

public interface PoetriesToTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoetriesToType record);

    int insertSelective(PoetriesToType record);

    PoetriesToType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoetriesToType record);

    int updateByPrimaryKey(PoetriesToType record);
}