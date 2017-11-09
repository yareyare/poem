package com.ivy.serviceImpl;

import com.ivy.model.BaseException;
import com.ivy.dao.PoemDetailMapper;
import com.ivy.model.po.PoemDetail;
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
    public int saveList(List<PoemDetail> list) throws BaseException {
        int retCnt = 0;
        try {
            for (PoemDetail poemDetailWithBLOBs : list){
                int ret = save(poemDetailWithBLOBs);
                retCnt += ret;
            }
        } catch (BaseException e) {
            throw e;
        }
        return retCnt;
    }

    @Override
    public int save(PoemDetail poemDetail) throws BaseException {
        try {
            int i = poemDetailMapper.insertSelective(poemDetail);
            return i;
        } catch (Exception e) {
            LOG.error("【PoemDetailServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }

    @Override
    public List<PoemDetail> getList(Integer poemId) throws BaseException {
        try {
            List<PoemDetail> poemDetailWithBLOBses = poemDetailMapper.selectList(poemId);
            return poemDetailWithBLOBses;
        } catch (Exception e) {
            LOG.error("【PoemDetailServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }
}
