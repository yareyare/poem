package com.ivy.dao;

import com.ivy.model.po.Poem;

import java.util.Map;

public interface PoemMapper {

    int insert(Poem record);

    int insertSelective(Poem record);

    Poem selectByPrimaryKey(Integer id);

    Poem selectByTitleDynastyAuthor(Map<String,Object> param);

    int updateByPrimaryKeySelective(Poem record);

    int updateByPrimaryKey(Poem record);
}