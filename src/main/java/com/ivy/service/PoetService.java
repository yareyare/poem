package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.Poet;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoetService {

    boolean add (String name ,Integer dynastyId) throws BaseException;

    boolean add (Poet poet) throws BaseException;

    Poet getByNameAndDynastyId(String name,Integer DynastyId) throws BaseException;

}
