package com.ivy.service;

import com.ivy.model.po.Session;

/**
 * Created by admin on 2017/11/22.
 */
public interface SessionService {
    Session getSessionById(Integer accessToken);

    int updateSession(Session session);

    void invalidOldSession(Integer id);

    boolean insertAndGetId(Session session);
}
