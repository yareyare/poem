package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.PoemDetail;
import com.ivy.model.vo.PoemDetailVO;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemDetailService {

    int saveList(List<PoemDetail> list) throws BaseException;

    int save(PoemDetail poemDetail) throws BaseException;

    List<PoemDetailVO> getList(Integer poemId) throws BaseException;
}
