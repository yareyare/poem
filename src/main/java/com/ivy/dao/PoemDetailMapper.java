package com.ivy.dao;

import com.ivy.model.po.PoemDetail;
import com.ivy.model.po.PoemDetailWithBLOBs;

public interface PoemDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoemDetailWithBLOBs record);

    int insertSelective(PoemDetailWithBLOBs record);

    PoemDetailWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoemDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PoemDetailWithBLOBs record);

    int updateByPrimaryKey(PoemDetail record);
}