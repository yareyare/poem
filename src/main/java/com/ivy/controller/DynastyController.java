package com.ivy.controller;

import com.ivy.model.BaseException;
import com.ivy.model.po.Dynasty;
import com.ivy.model.vo.DynastyVO;
import com.ivy.service.DynastyService;
import com.ivy.tool.Code;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2017/11/21.
 */

@RestController
@RequestMapping("dynasty")
public class DynastyController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private DynastyService dynastyService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public Return getAll(HttpServletRequest resuqest, HttpServletResponse response){
        try {
            List<DynastyVO> list = dynastyService.getAll();
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.note).put("list", list);
        } catch (BaseException e) {
            e.printStackTrace();
            logger.error("获取所有朝代失败", e);
            return Return.FAIL(Code.FAILT.code, Code.FAILT.note);
        }
    }

}
