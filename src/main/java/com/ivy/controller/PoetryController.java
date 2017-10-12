package com.ivy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ivy.model.bo.PoetriesIndexBO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ivy.service.PoetriesService;
import com.ivy.tool.Code;
import com.ivy.tool.Return;

@RestController
@RequestMapping
public class PoetryController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	PoetriesService poetriesService;
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String test(){
		try {
			List<PoetriesIndexBO> list = poetriesService.getIndexPoetries();
			ModelAndView model = new ModelAndView();
			for(PoetriesIndexBO p:list){
				System.out.println(p.getContent()+" "+p.getTitle());
			}
			model.addObject("list", list);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取首页诗歌内容失败", e);
			return "nopage";
		}
	}
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public Return getIndexPoetries(HttpServletRequest resuqest,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			List<PoetriesIndexBO> list = poetriesService.getIndexPoetries();
			for(PoetriesIndexBO p:list){
				System.out.println(p.getTitle());
				System.out.println(p.getAuthor());
				System.out.println(p.getContent());
			}
			return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取首页诗歌内容失败", e);
			return Return.FAIL(Code.FAILT.code, Code.FAILT.note);
		}
	}
}
