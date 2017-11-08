package com.ivy.dao;

import com.ivy.model.po.Poem;

public interface PoemMapper {

    int insert(Poem record);

    int insertSelective(Poem record);

    Poem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Poem record);

    int updateByPrimaryKey(Poem record);
}