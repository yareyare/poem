package com.ivy.service;

import java.util.List;

import com.ivy.model.po.Comment;

public interface CommentService {

	int add(Comment com) throws Exception;
	
	List<Comment> getByPoetriesId(String poetriesId) throws Exception;
	
}
