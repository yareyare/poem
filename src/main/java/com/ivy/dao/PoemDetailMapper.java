package com.ivy.dao;

import com.ivy.model.po.PoemDetail;

import java.util.List;

public interface PoemDetailMapper {

    int insert(PoemDetail record);

    int insertSelective(PoemDetail record);

    PoemDetail selectByPrimaryKey(Integer id);

    List<PoemDetail> selectList(Integer poemId);

    int updateByPrimaryKeySelective(PoemDetail record);

    int updateByPrimaryKey(PoemDetail record);
}