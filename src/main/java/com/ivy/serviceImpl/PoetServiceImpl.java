package com.ivy.serviceImpl;

import com.ivy.dao.PoetDetailMapper;
import com.ivy.model.BaseException;
import com.ivy.dao.PoetMapper;
import com.ivy.model.po.Poet;
import com.ivy.model.vo.PoetVO;
import com.ivy.service.PoetService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/11/8.
 */
@Service
public class PoetServiceImpl implements PoetService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoetMapper poetMapper;

    @Autowired
    private PoetDetailMapper poetDetailMapper;


    @Override
    public Integer add(String name, Integer dynastyId) throws BaseException {
        try {
            Poet poet = new Poet();
            poet.setCreateDate(new Date());
            poet.setDynastyId(dynastyId);
            poet.setName(name);
            Integer ret = add(poet);
            return ret;
        } catch (BaseException e) {
            LOG.error("【PoetServiceImpl.add(name,dynastyId)】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }

    @Override
    public Integer add(Poet poet) throws BaseException {
        try {
            int i = poetMapper.insertSelective(poet);
            if (i == 1){
                Map<String,Object> param = new HashMap<>();
                param.put("name",poet.getName());
                param.put("dynastyId",poet.getDynastyId());
                Poet retPoet = poetMapper.selectByNameAndDynastyId(param);
                return retPoet.getId();
            }
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.add(poet)】",e);
            throw new BaseException(Code.DB_ERROR);
        }
        return null;
    }

    @Override
    public Poet getByNameAndDynastyId(String name, Integer dynastyId) throws BaseException {
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("name",name);
            param.put("dynastyId",dynastyId);
            Poet poet = poetMapper.selectByNameAndDynastyId(param);
            return poet;
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.getByNameAndDynastyId】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }

    @Override
    public PoetVO getPoetById(Integer poetId) throws BaseException {
        try {
            PoetVO poet = poetMapper.selectById(poetId);
            return poet;
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.getPoetById】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }
}
