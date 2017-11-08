package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.PoemDetail;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemDetailService {

    int saveList(List<PoemDetail> list) throws BaseException;

    int save(PoemDetail poemDetail) throws BaseException;

    List<PoemDetail> getList(Integer poemId) throws BaseException;
}
