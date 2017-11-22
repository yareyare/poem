package com.ivy.serviceImpl;

import com.ivy.model.BaseException;
import com.ivy.dao.PoetDetailMapper;
import com.ivy.model.po.PoetDetail;
import com.ivy.model.vo.PoetDetailVO;
import com.ivy.service.PoetDetailService;
import com.ivy.tool.Code;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ivy on 2017/11/8.
 */
@Service
public class PoetDetailServiceImpl implements PoetDetailService {

    private static final Logger LOG = Logger.getLogger(PoetDetailServiceImpl.class);

    @Autowired
    private PoetDetailMapper poetDetailMapper;

    @Override
    public int saveList(List<PoetDetail> list) throws BaseException {
        int retCnt = 0;
        for (PoetDetail pd : list){
            int ret = save(pd);
            retCnt += ret;
        }
        return retCnt;
    }

    @Override
    public int save(PoetDetail poetDetail) throws BaseException {
        try {
            int i = poetDetailMapper.insertSelective(poetDetail);
            return i;
        }catch (Exception e){
            LOG.error("【PoetDetailServiceImpl.save(poetDetail)】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }


    @Override
    public List<PoetDetailVO> getList(Integer poetId) throws BaseException {
        try {
            List<PoetDetailVO> poetDetailVOList = poetDetailMapper.selectList(poetId);
            return poetDetailVOList;
        } catch (Exception e) {
            LOG.error("【PoetServiceImpl.getList】",e);
            throw new BaseException(Code.DB_ERROR);
        }
    }
}
