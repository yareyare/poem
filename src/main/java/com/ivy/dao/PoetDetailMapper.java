package com.ivy.dao;

import com.ivy.model.po.PoetDetail;
import com.ivy.model.vo.PoetDetailVO;

import java.util.List;

public interface PoetDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PoetDetail record);

    int insertSelective(PoetDetail record);

    PoetDetail selectByPrimaryKey(Integer id);

    List<PoetDetailVO> selectList(Integer poetId);

    int updateByPrimaryKeySelective(PoetDetail record);

    int updateByPrimaryKey(PoetDetail record);
}