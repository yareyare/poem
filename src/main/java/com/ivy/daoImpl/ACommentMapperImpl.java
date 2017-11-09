package com.ivy.daoImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ivy.exception.BaseException;
import com.ivy.model.po.Comment;

@Repository
public class ACommentMapperImpl extends BaseDao  {

	public int deleteByPrimaryKey(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.delete("com.ivy.dao.CommentMapper.deleteByPrimaryKey", id);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insert(Comment record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.insert("com.ivy.dao.CommentMapper.insert", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int insertSelective(Comment record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.insert("com.ivy.dao.CommentMapper.insertSelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public Comment selectByPrimaryKey(Integer id) throws Exception {
		SqlSession session = sessionFactory.openSession();
		Comment comment = null;
		try {
			comment = session.selectOne("com.ivy.dao.CommentMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return comment;
	}

	public int updateByPrimaryKeySelective(Comment record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.CommentMapper.updateByPrimaryKeySelective", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public int updateByPrimaryKey(Comment record) throws Exception {
		SqlSession session = sessionFactory.openSession();
		int ret = 0;
		try {
			ret = session.update("com.ivy.dao.CommentMapper.updateByPrimaryKey", record);
			session.commit();
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return ret;
	}

	public List<Comment> getByPoetriesId(String poetriesId) throws Exception {
		SqlSession session = sessionFactory.openSession();
		List<Comment> list = null;
		try {
			list = session.selectOne("com.ivy.dao.CommentMapper.getByPoetriesId", poetriesId);
		} catch (Exception e) {
			throw new BaseException(BaseException.DB_EXCEPTION, e);
		} finally {
			session.close();
		}
		return list;
	}
	
	

}
