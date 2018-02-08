package com.ivy.service;

import com.ivy.model.BaseException;
import com.ivy.model.po.Poem;
import com.ivy.model.vo.PoemVO;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemService {

    Integer save(Poem poemCrawl)throws BaseException;

    Poem getByTitleAndDynastyAndAuthor(String title, Integer dynastyId,Integer poetId) throws BaseException;

    PoemVO getById(Integer id) throws BaseException;

    List<PoemVO> getIndexPoem()throws BaseException;

    Poem getByRefId(Integer refId) throws BaseException;

}
