package com.ivy.daoImpl;

import java.util.List;

import com.ivy.model.bo.PoetriesIndexBO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ivy.dao.PoetriesMapper;
import com.ivy.exception.BaseException;
import com.ivy.model.po.Poetries;

@Repository
public class PoetriesMapperImpl extends BaseDao implements PoetriesMapper{

	public int deleteByPrimaryKey(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.delete("com.ivy.dao.PoetriesMapper.deleteByPrimaryKey", id);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insert(Poetries record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.insert("com.ivy.dao.PoetriesMapper.insert", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insertSelective(Poetries record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.insert("com.ivy.dao.PoetriesMapper.insertSelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public Poetries selectByPrimaryKey(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();
		Poetries p = null;
		try {
			p = session.selectOne("com.ivy.dao.PoetriesMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return p;
	}

	public int updateByPrimaryKeySelective(Poetries record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.PoetriesMapper.updateByPrimaryKeySelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int updateByPrimaryKeyWithBLOBs(Poetries record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.PoetriesMapper.updateByPrimaryKeyWithBLOBs", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int updateByPrimaryKey(Poetries record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.PoetriesMapper.updateByPrimaryKey", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public List<PoetriesIndexBO> getIndexPoetries() throws Exception {
		SqlSession session = sessionFactory.openSession();
		List<PoetriesIndexBO> list = null;
		try {
			list = session.selectList("com.ivy.dao.PoetriesMapper.getIndexPoetries");
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return list;
	}

	
}
