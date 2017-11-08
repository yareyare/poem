package com.ivy.dao;

import com.ivy.model.po.Poet;

public interface PoetMapper {

    int insert(Poet record);

    int insertSelective(Poet record);

    Poet selectByPrimaryKey(Integer id);

    Poet selectByNameAndDynastyId(String name,Integer dynastyId);

    int updateByPrimaryKeySelective(Poet record);

    int updateByPrimaryKey(Poet record);
}