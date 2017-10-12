package com.ivy.daoImpl;

import com.ivy.dao.ReConnectMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/27.
 */
@Repository
public class ReConnectMapperImpl extends BaseDao implements ReConnectMapper {

	public Integer reConnection() {
		int ret ;
        SqlSession session = null;
        try{
            session  = sessionFactory.openSession();;
            ret =session.selectOne("com.ivy.dao.ReConnectMapper.reConnection");
        }finally{
            session.close();
        }
        return ret;
	}
	
}
