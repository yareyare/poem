package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.PoemDetail;
import com.ivy.model.po.PoemDetailWithBLOBs;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemDetailService {

    int saveList(List<PoemDetailWithBLOBs> list) throws BaseException;

    int save(PoemDetailWithBLOBs poemDetail) throws BaseException;

    List<PoemDetailWithBLOBs> getList(Integer poemId) throws BaseException;
}
