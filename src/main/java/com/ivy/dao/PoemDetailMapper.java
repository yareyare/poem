package com.ivy.dao;

import com.ivy.model.po.PoemDetail;
import com.ivy.model.vo.PoemDetailVO;

import java.util.List;

public interface PoemDetailMapper {

    int insert(PoemDetail record);

    int insertSelective(PoemDetail record);

    PoemDetail selectByPrimaryKey(Integer id);

    List<PoemDetailVO> selectList(Integer poemId);

    int updateByPrimaryKeySelective(PoemDetail record);

    int updateByPrimaryKey(PoemDetail record);
}