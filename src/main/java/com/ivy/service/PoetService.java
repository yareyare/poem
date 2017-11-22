package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.Poet;
import com.ivy.model.vo.PoetDetailVO;
import com.ivy.model.vo.PoetVO;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoetService {

    Integer add(String name, Integer dynastyId) throws BaseException;

    Integer add(Poet poet) throws BaseException;

    Poet getByNameAndDynastyId(String name, Integer DynastyId) throws BaseException;

    PoetVO getPoetById(Integer poetId) throws BaseException;

}
