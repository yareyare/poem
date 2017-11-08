package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.PoemType;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemTypeService {

    Integer save(String type1,String type2) throws BaseException;

    PoemType get(String type1, String type2) throws BaseException;

}
