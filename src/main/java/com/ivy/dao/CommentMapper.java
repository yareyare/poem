package com.ivy.dao;

import java.util.List;

import com.ivy.model.po.Comment;

public interface CommentMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Comment record) throws Exception;

    int insertSelective(Comment record) throws Exception;

    Comment selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Comment record) throws Exception;

    int updateByPrimaryKey(Comment record) throws Exception;
    
    List<Comment> getByPoetriesId(String poetriesId) throws Exception; 
}