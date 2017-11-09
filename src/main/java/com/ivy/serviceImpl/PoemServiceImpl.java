package com.ivy.serviceImpl;

import com.ivy.model.BaseException;
import com.ivy.dao.PoemMapper;
import com.ivy.model.po.Poem;
import com.ivy.service.PoemService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/11/8.
 */
@Service
public class PoemServiceImpl implements PoemService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoemMapper poemMapper;

    @Override
    public Integer save(Poem poem) throws BaseException {
        Integer id = null;

        int i = 0;
        try {
            Poem poem1 = getByTitleAndDynastyAndAuthor(poem.getTitle(), poem.getDynastyId(), poem.getPoetId());
            if (poem1 != null) {
                id = poem1.getId();
            } else {
                i = poemMapper.insertSelective(poem);
                if (i == 1) {
                    Poem resultPoem = getByTitleAndDynastyAndAuthor(poem.getTitle(), poem.getDynastyId(), poem.getPoetId());
                    id = resultPoem.getId();
                }
            }
        } catch (BaseException e) {
            LOG.error("【PoemServiceImpl.getByTitleAndDynastyAndAuthor】", e);
            throw new BaseException(Code.DB_ERROR);
        }
        return id;
    }

    @Override
    public Poem getByTitleAndDynastyAndAuthor(String title, Integer dynastyId, Integer poetId) throws BaseException {
        Poem poem = null;
        try {
            poem = poemMapper.selectByTitleDynastyAuthor(title, dynastyId, poetId);
        } catch (Exception e) {
            LOG.error("【PoemServiceImpl.getByTitleAndDynastyAndAuthor】", e);
            throw new BaseException(Code.DB_ERROR);
        }

        return poem;
    }
}
