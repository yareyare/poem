package com.ivy.serviceImpl;

import com.ivy.core.model.BaseException;
import com.ivy.dao.PoetMapper;
import com.ivy.model.po.Poet;
import com.ivy.service.PoetService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by admin on 2017/11/8.
 */
@Service
public class PoetServiceImpl implements PoetService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoetMapper poetMapper;


    @Override
    public boolean add(String name, Integer dynastyId) throws BaseException {
        try {
            Poet poet = new Poet();
            poet.setCreateDate(new Date());
            poet.setDynastyId(dynastyId);
            poet.setName(name);
            boolean ret = add(poet);
            return ret;
        } catch (BaseException e) {
            LOG.error("【PoetServiceImpl.add(name,dynastyId)】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }

    @Override
    public boolean add(Poet poet) throws BaseException {
        try {
            int i = poetMapper.insertSelective(poet);
            if (i == 1){
                return true;
            }
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.add(poet)】",e);
            throw new BaseException(Code.DB_ERROR);
        }
        return false;
    }

    @Override
    public Poet getByNameAndDynastyId(String name, Integer dynastyId) throws BaseException {
        try {
            Poet poet = poetMapper.selectByNameAndDynastyId(name, dynastyId);
            return poet;
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.getByNameAndDynastyId】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }
}
