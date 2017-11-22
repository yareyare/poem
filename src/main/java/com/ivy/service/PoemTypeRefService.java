package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.PoemTypeRef;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemTypeRefService {

    boolean add(Integer poemId, Integer typeId)throws BaseException;

    PoemTypeRef get(Integer poemId, Integer typeId) throws BaseException;
}
