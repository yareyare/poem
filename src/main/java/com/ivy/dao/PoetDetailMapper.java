package com.ivy.dao;

import com.ivy.model.po.PoetDetail;

import java.util.List;

public interface PoetDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PoetDetail record);

    int insertSelective(PoetDetail record);

    PoetDetail selectByPrimaryKey(Integer id);

    List<PoetDetail> selectList(Integer poetId);

    int updateByPrimaryKeySelective(PoetDetail record);

    int updateByPrimaryKey(PoetDetail record);
}