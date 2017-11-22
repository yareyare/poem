package com.ivy.dao;

import com.ivy.model.po.Poet;

import java.util.Map;

public interface PoetMapper {

    int insert(Poet record);

    int insertSelective(Poet record);

    Poet selectByPrimaryKey(Integer id);

    Poet selectByNameAndDynastyId(Map<String,Object> param);

    int updateByPrimaryKeySelective(Poet record);

    int updateByPrimaryKey(Poet record);
}