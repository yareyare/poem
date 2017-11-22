package com.ivy.dao;

import com.ivy.model.po.Session;

public interface SessionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Session record);

    int insertSelective(Session record);

    Session selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Session record);

    int updateByPrimaryKey(Session record);
}