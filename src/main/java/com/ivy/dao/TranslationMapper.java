package com.ivy.dao;

import com.ivy.model.po.Translation;

public interface TranslationMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Translation record) throws Exception;

    int insertSelective(Translation record) throws Exception;

    Translation selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Translation record) throws Exception;

    int updateByPrimaryKey(Translation record) throws Exception;
}