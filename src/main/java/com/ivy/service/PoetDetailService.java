package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.PoetDetail;
import com.ivy.model.vo.PoetDetailVO;

import java.util.List;

/**
 * Created by ivy on 2017/11/8.
 */
public interface PoetDetailService {

    int saveList(List<PoetDetail> list) throws BaseException;

    int save(PoetDetail PoetDetail) throws BaseException;

    List<PoetDetailVO> getList(Integer poetId) throws BaseException;

}
