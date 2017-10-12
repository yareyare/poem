package com.ivy.dao;

import java.util.List;

import com.ivy.model.po.Poetries;
import com.ivy.model.bo.PoetriesIndexBO;

public interface PoetriesMapper {
	
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Poetries record) throws Exception;

    int insertSelective(Poetries record)  throws Exception;

    Poetries selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Poetries record) throws Exception;

    int updateByPrimaryKeyWithBLOBs(Poetries record) throws Exception;

    int updateByPrimaryKey(Poetries record) throws Exception;
    
    List<PoetriesIndexBO> getIndexPoetries() throws Exception;
}