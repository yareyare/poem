package com.ivy.controller;

import com.ivy.tool.JsonConvert;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivy.exception.BaseException;
import com.ivy.model.po.Comment;
import com.ivy.service.CommentService;
import com.ivy.tool.Code;
import com.ivy.tool.Return;

@Controller
@RequestMapping(value="comment")
public class CommentController {

	private final Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	CommentService comentService;
	
	@RequestMapping(value="add",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Return addComment(@RequestBody Comment comment)throws Exception{
		LOG.info("【comment／add】"+ JsonConvert.toJson(comment));
		try {
			comentService.add(comment);
		} catch (Exception e) {
			LOG.error("添加评论失败", e);
			throw new BaseException("添加评论失败");
		}
		
		return Return.FAIL(Code.FAILT.code,Code.FAILT.note);
	}
}
