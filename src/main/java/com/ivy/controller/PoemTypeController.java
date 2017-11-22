package com.ivy.controller;

import com.ivy.model.BaseException;
import com.ivy.model.vo.SubTypeVO;
import com.ivy.model.vo.TypeVO;
import com.ivy.service.PoemTypeService;
import com.ivy.tool.Code;
import com.ivy.tool.EnumUtils;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("type")
public class PoemTypeController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PoemTypeService poemTypeService;

    @RequestMapping(value="getType",method= RequestMethod.GET)
    public Return getType(HttpServletRequest resuqest, HttpServletResponse response){
        //response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            List<TypeVO> list = poemTypeService.getType();
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("list", list);
        } catch (BaseException e) {
            logger.error("获取诗歌类型失败", e);
            return Return.FAIL( Integer.parseInt( e.getMessage() ), EnumUtils.getNameByType( Code.class, Integer.parseInt( e.getMessage() ) ) );
        }
    }

    @RequestMapping(value="getSubType",method= RequestMethod.GET)
    public Return getSubType(HttpServletRequest resuqest, HttpServletResponse response ,@RequestParam String type){
        //response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            List<SubTypeVO> list = poemTypeService.getSubType(type);
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("list", list);
        } catch (BaseException e) {
            logger.error("获取诗歌类型失败", e);
            return Return.FAIL( Integer.parseInt( e.getMessage() ), EnumUtils.getNameByType( Code.class, Integer.parseInt( e.getMessage() ) ) );
        }
    }
}
