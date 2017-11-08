package com.ivy.dao;

import com.ivy.model.po.PoemDetail;
import com.ivy.model.po.PoemDetailWithBLOBs;

import java.util.List;

public interface PoemDetailMapper {

    int insert(PoemDetailWithBLOBs record);

    int insertSelective(PoemDetailWithBLOBs record);

    PoemDetailWithBLOBs selectByPrimaryKey(Integer id);

    List<PoemDetailWithBLOBs> selectList(Integer poemId);

    int updateByPrimaryKeySelective(PoemDetailWithBLOBs record);

    int updateByPrimaryKey(PoemDetail record);
}