package com.ivy.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ivy.dao.SessionMapper;
import com.ivy.model.po.Session;

@Repository
public class SessionMapperImpl extends BaseDao implements SessionMapper {

	public int insert(Session record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int cnt = 0;
		try {
			cnt = session.insert("com.ivy.dao.SessionMapper.insert", record);
			session.commit();
		} finally {
			session.close();
		}
		return cnt;
	}

	public Integer insertAndGetId(Session record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int cnt = 0;

		try {
			cnt = session.insert("com.ivy.dao.SessionMapper.insert", record);
			session.commit();
			if (cnt > 0) {
				return record.getId();
			} else {
				return null;
			}
		} finally {
			session.close();
		}
	}

	public int insertSelective(Session record) throws Exception {
		return 0;
	}

	public Session selectByPrimaryKey(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();

		try {
			Session record = session.selectOne("com.ivy.dao.SessionMapper.selectByPrimaryKey", id);
			return record;
		} finally {
			session.close();
		}
	}

	public Session validateSession(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();

		try {
			Session record = session.selectOne("com.ivy.dao.SessionMapper.validateSession", id);
			return record;
		} finally {
			session.close();
		}
	}

	public int updateByPrimaryKeySelective(Session record) throws Exception {
		int ret = 0;
		SqlSession session = sessionFactory.openSession();

		try {
			ret = session.update("com.ivy.dao.SessionMapper.updateByPrimaryKeySelective", record);
			session.commit();
		} finally {
			session.close();
		}
		return ret;
	}

	public int updateByPrimaryKey(Session record) throws Exception {
		return 0;
	}

	public List<Session> getSessionByUserid(Integer userId) throws Exception {
		List<Session> list = null;
		SqlSession session = sessionFactory.openSession();
		list = session.selectList("com.ivy.dao.SessionMapper.selectByUserid", userId);
		return list;
	}
}
