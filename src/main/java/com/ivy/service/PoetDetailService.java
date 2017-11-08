package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.PoetDetail;

import java.util.List;

/**
 * Created by ivy on 2017/11/8.
 */
public interface PoetDetailService {

    int saveList(List<PoetDetail> list) throws BaseException;

    int save(PoetDetail PoetDetail) throws BaseException;

    List<PoetDetail> getList(Integer poetId) throws BaseException;

}
