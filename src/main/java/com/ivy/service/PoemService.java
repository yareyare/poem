package com.ivy.service;

import com.ivy.core.model.BaseException;
import com.ivy.model.po.Poem;

/**
 * Created by admin on 2017/11/8.
 */
public interface PoemService {

    Integer save(Poem poemCrawl)throws BaseException;

    Poem getByTitleAndDynastyAndAuthor(String title, Integer dynastyId,Integer poetId) throws BaseException;

}
