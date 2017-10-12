package com.ivy.dao;

import com.ivy.model.po.Praise;

public interface PraiseMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Praise record) throws Exception;

    int insertSelective(Praise record) throws Exception;

    Praise selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Praise record) throws Exception;

    int updateByPrimaryKey(Praise record) throws Exception;
}