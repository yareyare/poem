package com.ivy.service;

import com.ivy.model.BaseException;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemTypeRefService {

    boolean add(Integer poemId,Integer typeId)throws BaseException;

}
