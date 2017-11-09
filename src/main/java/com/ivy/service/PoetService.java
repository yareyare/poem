package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.Poet;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoetService {

    Integer add (String name ,Integer dynastyId) throws BaseException;

    Integer add (Poet poet) throws BaseException;

    Poet getByNameAndDynastyId(String name,Integer DynastyId) throws BaseException;

}
