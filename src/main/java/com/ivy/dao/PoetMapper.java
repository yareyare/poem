package com.ivy.dao;

import com.ivy.model.po.Poet;
import com.ivy.model.vo.PoetDetailVO;
import com.ivy.model.vo.PoetVO;

import java.util.List;
import java.util.Map;

public interface PoetMapper {

    int insert(Poet record) throws Exception;

    int insertSelective(Poet record) throws Exception;

    Poet selectByPrimaryKey(Integer id) throws Exception;

    Poet selectByNameAndDynastyId(Map<String,Object> param) throws Exception;

    int updateByPrimaryKeySelective(Poet record) throws Exception;

    int updateByPrimaryKey(Poet record) throws Exception;

    PoetVO selectById(Integer poetId) throws Exception;
}