package com.ivy.controller;

import com.ivy.model.BaseException;
import com.ivy.model.vo.PoetDetailVO;
import com.ivy.model.vo.PoetVO;
import com.ivy.service.PoetDetailService;
import com.ivy.service.PoetService;
import com.ivy.tool.Code;
import com.ivy.tool.EnumUtils;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by admin on 2017/11/21.
 */

@RestController
@RequestMapping("poet")
public class PoetController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PoetService poetService;

    @Autowired
    private PoetDetailService poetDetailService;

    //根据id获取去poet 简介 和 详情
    @RequestMapping(value = "getPoetById", method = RequestMethod.GET)
    public Return getPoetById(Integer poetId) {
        PoetVO poetVo;
        List<PoetDetailVO> list;
        try {
            poetVo = poetService.getPoetById(poetId);
            list = poetDetailService.getList(poetId);
        } catch (BaseException e) {
            return Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }

        return Return.SUCCESS(Code.SUCCESS).put("poet", poetVo).put("poetDetailList", list);
    }


}
