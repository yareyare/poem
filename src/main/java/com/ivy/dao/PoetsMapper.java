package com.ivy.dao;

import com.ivy.model.po.Poets;

public interface PoetsMapper {
    int deleteByPrimaryKey(Integer id)  throws Exception;

    int insert(Poets record) throws Exception;

    int insertSelective(Poets record) throws Exception;

    Poets selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Poets record) throws Exception;

    int updateByPrimaryKey(Poets record) throws Exception;
}