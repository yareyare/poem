package com.ivy.dao;

import com.ivy.model.po.PoemType;
import com.ivy.model.vo.SubTypeVO;
import com.ivy.model.vo.TypeVO;

import java.util.List;
import java.util.Map;

public interface PoemTypeMapper {

    int insert(PoemType record) throws Exception;

    int insertSelective(PoemType record) throws Exception;

    PoemType selectByPrimaryKey(Integer id) throws Exception;

    PoemType selectByTypeAndType1(Map<String,String> param) throws Exception;

    int updateByPrimaryKeySelective(PoemType record) throws Exception;

    int updateByPrimaryKey(PoemType record) throws Exception;

    List<TypeVO> selectType() throws Exception;

    List<SubTypeVO> selectSubType(String type) throws Exception;
}