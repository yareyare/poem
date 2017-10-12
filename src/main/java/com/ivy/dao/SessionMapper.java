package com.ivy.dao;

import java.util.List;

import com.ivy.model.po.Session;

public interface SessionMapper {
	
    int insert(Session record) throws Exception;

    int insertSelective(Session record) throws Exception;

    Integer insertAndGetId(Session record) throws Exception;

    Session selectByPrimaryKey(Integer id) throws Exception;

    Session validateSession(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Session record) throws Exception;

    int updateByPrimaryKey(Session record) throws Exception;
    
    List<Session> getSessionByUserid(Integer uid) throws Exception;
}