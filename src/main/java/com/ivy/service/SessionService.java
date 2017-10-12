package com.ivy.service;

import java.util.List;

import com.ivy.model.po.Session;

public interface SessionService {

	public int insert(Session record) throws Exception;

	public Integer insertAndGetId(Session record) throws Exception;

	public Session validateSession(Integer id) throws Exception;

	public int updateSession(Session session) throws Exception;

	public List<Session> getSessionByUserid(Integer uid) throws Exception;

	public Session getSessionById(Integer id) throws Exception;

	public boolean invalidOldSession(Integer uid) throws Exception;
}
