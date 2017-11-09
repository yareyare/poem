package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.Dynasty;

/**
 * Created by admin on 2017/11/8.
 */
public interface DynastyService {

    Integer save(Dynasty dynasty) throws BaseException;

    Dynasty get(String name) throws BaseException;

}
