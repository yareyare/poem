package com.ivy.dao;

import java.util.Map;

import com.ivy.model.po.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id)  throws Exception;

    int insert(Users record)  throws Exception;

    int insertSelective(Users record)  throws Exception;

    Users selectByPrimaryKey(Integer id)  throws Exception;

    int updateByPrimaryKeySelective(Users record)  throws Exception;

    int updateByPrimaryKey(Users record)  throws Exception;
    
    Users selectUserByParamsPassword(Map<String,String> param) throws Exception;
    
    Users selectUserByParams(Map<String,String> param) throws Exception;
	
}