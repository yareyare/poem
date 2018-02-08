package com.ivy.controller;

import com.ivy.model.BaseException;
import com.ivy.model.vo.PoemDetailVO;
import com.ivy.model.vo.PoemVO;
import com.ivy.service.PoemDetailService;
import com.ivy.service.PoemService;
import com.ivy.tool.Code;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017/11/21.
 */

@RestController
@RequestMapping
public class PoemController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PoemService poemService;

    @Autowired
    private PoemDetailService poemDetailService;

    //首页显示的诗歌
    @RequestMapping(value="index",method= RequestMethod.GET)
    public Return getIndexPoetries(HttpServletRequest resuqest, HttpServletResponse response){
       //response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            List<PoemVO> list = poemService.getIndexPoem();
            for(PoemVO p:list){
                System.out.println(p.getTitle());
                System.out.println(p.getPoetName());
                System.out.println(p.getContent());
            }
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("poemList", list);
        } catch (BaseException e) {
            logger.error("获取首页诗歌内容失败", e);
            return Return.FAIL(Code.FAILT.code, Code.FAILT.note);
        }
    }

    //根据id获取诗歌详情，包括译注赏列表
    @RequestMapping(value = "getDetail", method = RequestMethod.GET)
    public Return getDetail(HttpServletRequest resuqest, HttpServletResponse response, @RequestParam Integer poemId){
        //response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            PoemVO poem = poemService.getById(poemId);
            List<PoemDetailVO> list = poemDetailService.getList(poemId);
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("poem",poem).put("poemDetailList", list);
        } catch (BaseException e) {
            e.printStackTrace();
            logger.error("获取首页诗歌内容失败", e);
            return Return.FAIL(Code.FAILT.code, Code.FAILT.note);
        }
    }
}
