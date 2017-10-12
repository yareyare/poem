package com.ivy.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.SessionMapper;
import com.ivy.model.po.Session;
import com.ivy.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{
  private static final Log LOGGER = LogFactory.getLog(SessionService.class.getName());

  @Autowired
  SessionMapper sessionMapperImpl;

  public int insert(Session record) {
    try {
      int cnt = sessionMapperImpl.insert(record);
      return cnt;
    }catch(Exception e){
      e.printStackTrace();
      LOGGER.error("新增session失败",e);
      return 0;
    }

  }

  public Integer insertAndGetId(Session record) {
    try {
      Integer id = sessionMapperImpl.insertAndGetId(record);
      return id;
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error("新增session失败", e);
      return null;
    }
  }

    public int updateSession(Session session) {
        int ret = 0;
        try {
            ret = sessionMapperImpl.updateByPrimaryKeySelective(session);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("校验session失败", e);
        }
        return ret;
    }

    public List<Session> getSessionByUserid(Integer uid){
        List<Session> list = null;
        try {
            list = sessionMapperImpl.getSessionByUserid(uid);
        } catch (Exception e) {
            LOGGER.error("获取session失败", e);
            e.printStackTrace();
        }
        return list;
    }
    
    public Session getSessionById(Integer id){
        Session session = null;
        try {
            session = sessionMapperImpl.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("获取session失败", e);
            e.printStackTrace();
        }
        return session;
    }

    public boolean invalidOldSession(Integer uid){
        try {
            List<Session> list = getSessionByUserid(uid);
            for (Session s : list) {
                s.setStatus(0);
                updateSession(s);
            }
        }catch (Exception e){
            LOGGER.error("旧token状态改为0，失败");
            return false;
        }
        return true;
    }

	public Session validateSession(Integer id) throws Exception {
		 try {
		      Session ses = sessionMapperImpl.validateSession(id);
		      return ses;
		    }catch(Exception e){
		      e.printStackTrace();
		      LOGGER.error("校验session失败",e);
		      return null;
		    }
	}
}
