package com.ivy.dao;

import com.ivy.model.po.Poem;
import com.ivy.model.vo.PoemVO;

import java.util.List;
import java.util.Map;

public interface PoemMapper {

    int insert(Poem record) throws Exception;

    int insertSelective(Poem record) throws Exception;

    Poem selectByPrimaryKey(Integer id) throws Exception;

    PoemVO selectById(Integer id) throws Exception;

    Poem selectByTitleDynastyAuthor(Map<String,Object> param) throws Exception;

    int updateByPrimaryKeySelective(Poem record) throws Exception;

    int updateByPrimaryKey(Poem record) throws Exception;

    List<PoemVO> selectIndexPoem() throws Exception;
}