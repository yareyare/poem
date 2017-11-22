package com.ivy.dao;

import com.ivy.model.po.Dynasty;
import com.ivy.model.vo.DynastyVO;

import java.util.List;

public interface DynastyMapper {

    int insert(Dynasty record) throws Exception;

    int insertSelective(Dynasty record) throws Exception;

    Dynasty selectByPrimaryKey(Integer id) throws Exception;

    Dynasty selectByDynastyName(String name) throws Exception;

    int updateByPrimaryKeySelective(Dynasty record) throws Exception;

    int updateByPrimaryKey(Dynasty record) throws Exception;

    List<DynastyVO> selectAll() throws Exception;
}