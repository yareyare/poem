package com.ivy.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PoetryController {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	//@Autowired
	//PoetriesService poetriesService;
	
//	@RequestMapping(value="test",method=RequestMethod.GET)
//	public String test(){
//		try {
//			List<PoetriesIndexBO> list = poetriesService.getIndexPoetries();
//			ModelAndView model = new ModelAndView();
//			for(PoetriesIndexBO p:list){
//				System.out.println(p.getContent()+" "+p.getTitle());
//			}
//			model.addObject("list", list);
//			return "index";
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("获取首页诗歌内容失败", e);
//			return "nopage";
//		}
//	}
//
//	@RequestMapping(value="index",method=RequestMethod.GET)
//	public Return getIndexPoetries(HttpServletRequest resuqest,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		try {
//			List<PoetriesIndexBO> list = poetriesService.getIndexPoetries();
//			for(PoetriesIndexBO p:list){
//				System.out.println(p.getTitle());
//				System.out.println(p.getAuthor());
//				System.out.println(p.getContent());
//			}
//			return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("list", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("获取首页诗歌内容失败", e);
//			return Return.FAIL(Code.FAILT.code, Code.FAILT.note);
//		}
//	}
}
