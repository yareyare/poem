package com.ivy.serviceImpl;

import com.ivy.core.model.BaseException;
import com.ivy.dao.PoemDetailMapper;
import com.ivy.dao.PoemMapper;
import com.ivy.model.po.PoemDetail;
import com.ivy.model.po.PoemDetailWithBLOBs;
import com.ivy.service.PoemDetailService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */

@Service
public class PoemDetailServiceImpl implements PoemDetailService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoemDetailMapper poemDetailMapper;

    @Override
    public int saveList(List<PoemDetailWithBLOBs> list) throws BaseException {
        int retCnt = 0;
        try {
            for (PoemDetailWithBLOBs poemDetailWithBLOBs : list){
                int ret = save(poemDetailWithBLOBs);
                retCnt += ret;
            }
        } catch (BaseException e) {
            throw e;
        }
        return retCnt;
    }

    @Override
    public int save(PoemDetailWithBLOBs poemDetail) throws BaseException {
        try {
            int i = poemDetailMapper.insertSelective(poemDetail);
            return i;
        } catch (Exception e) {
            LOG.error("【PoemDetailServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }

    @Override
    public List<PoemDetailWithBLOBs> getList(Integer poemId) throws BaseException {
        try {
            List<PoemDetailWithBLOBs> poemDetailWithBLOBses = poemDetailMapper.selectList(poemId);
            return poemDetailWithBLOBses;
        } catch (Exception e) {
            LOG.error("【PoemDetailServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }
}
