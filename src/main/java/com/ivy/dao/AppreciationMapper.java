package com.ivy.dao;

import com.ivy.model.po.Appreciation;

public interface AppreciationMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Appreciation record) throws Exception;

    int insertSelective(Appreciation record) throws Exception;

    Appreciation selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Appreciation record) throws Exception;

    int updateByPrimaryKey(Appreciation record) throws Exception;
}