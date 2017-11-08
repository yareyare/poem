package com.ivy.serviceImpl;

import com.ivy.core.model.BaseException;
import com.ivy.dao.PoemTypeRefMapper;
import com.ivy.model.po.PoemTypeRef;
import com.ivy.service.PoemTypeRefService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by admin on 2017/11/8.
 */
@Service
public class PoemTypeRefServiceImpl implements PoemTypeRefService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoemTypeRefMapper poemTypeRefMapper;

    @Override
    public boolean add(Integer poemId, Integer typeId) throws BaseException {
        try {
            PoemTypeRef poemTypeRef = new PoemTypeRef();
            poemTypeRef.setCreateDate(new Date());
            poemTypeRef.setPoemId(poemId);
            poemTypeRef.setPoemTypeId(typeId);
            int i = poemTypeRefMapper.insertSelective(poemTypeRef);
            if (i == 1) {
                return true;
            }
        } catch (Exception e) {
            LOG.error("【PoemTypeRefServiceImpl.add】", e);
            throw new BaseException(Code.DB_ERROR);
        }
        return false;
    }
}
