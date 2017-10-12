package com.ivy.dao;

import com.ivy.model.po.Picture;

public interface PictureMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Picture record) throws Exception;

    int insertSelective(Picture record) throws Exception;

    Picture selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Picture record) throws Exception;

    int updateByPrimaryKey(Picture record) throws Exception;
}