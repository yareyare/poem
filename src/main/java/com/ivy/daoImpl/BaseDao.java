package com.ivy.daoImpl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	SqlSessionFactory sessionFactory;
	
}
