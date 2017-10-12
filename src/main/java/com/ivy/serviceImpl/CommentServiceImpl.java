package com.ivy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.CommentMapper;
import com.ivy.model.po.Comment;
import com.ivy.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	public int add(Comment com) throws Exception {
		return commentMapper.insertSelective(com);
	}

	public List<Comment> getByPoetriesId(String poetriesId) throws Exception {
		return commentMapper.getByPoetriesId(poetriesId);
	}

}
