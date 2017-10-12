package com.ivy.daoImpl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ivy.dao.UsersMapper;
import com.ivy.exception.BaseException;
import com.ivy.model.po.Users;
import com.ivy.tool.Md5Tool;

@Repository
public class UsersMapperImpl extends BaseDao implements UsersMapper {

	public int deleteByPrimaryKey(Integer id) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.delete("com.ivy.dao.UsersMapper.deleteByPrimaryKey", id);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insert(Users record) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			record.setPassword(Md5Tool.string2MD5(record.getPassword()));//加密
			ret = session.insert("com.ivy.dao.UsersMapper.insert", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insertSelective(Users record) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			record.setPassword(Md5Tool.string2MD5(record.getPassword()));//加密
			ret = session.insert("com.ivy.dao.UsersMapper.insertSelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public Users selectByPrimaryKey(Integer id) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		Users users = null;
		try {
			users = session.selectOne("com.ivy.dao.UsersMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return users;
	}

	public int updateByPrimaryKeySelective(Users record) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.UsersMapper.updateByPrimaryKeySelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int updateByPrimaryKey(Users record) throws BaseException {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.UsersMapper.updateByPrimaryKey", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public Users selectUserByParams(Map<String, String> param) throws Exception {
		SqlSession session = sessionFactory.openSession();
		Users users = null;
		try {
			users = session.selectOne("com.ivy.dao.UsersMapper.selectUserByParams", param);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return users;
	}

	public Users selectUserByParamsPassword(Map<String, String> param) throws Exception {
		SqlSession session = sessionFactory.openSession();
		Users users = null;
		try {
			users = session.selectOne("com.ivy.dao.UsersMapper.selectUserByParamsPassword", param);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return users;
	}

}
