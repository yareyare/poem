package com.ivy.db;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2017/8/14.
 */
public class DB_client {

    private static final Logger LOGGER = Logger.getLogger(DB_client.class.getName());

    private static SqlSessionFactory sessionFactory = null;
    public static SqlSessionFactory init_pool() {
        String resource = "conf/mybatis-config.xml";
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        }catch(IOException e){
            e.printStackTrace();

            LOGGER.error("初始化数据库连接池异常",e);
        }
        return sessionFactory;
    }

    public static SqlSession createSession() {
        SqlSession session = sessionFactory.openSession();


        return session;
    }
}
