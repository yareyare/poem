package com.ivy.dao;

import com.ivy.model.po.Poem;

public interface PoemMapper {

    int insert(Poem record);

    int insertSelective(Poem record);

    Poem selectByPrimaryKey(Integer id);

    Poem selectByTitleDynastyAuthor(String title,Integer dynasty ,Integer author);

    int updateByPrimaryKeySelective(Poem record);

    int updateByPrimaryKey(Poem record);
}