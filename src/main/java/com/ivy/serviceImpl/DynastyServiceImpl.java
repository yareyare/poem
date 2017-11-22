package com.ivy.serviceImpl;

import com.ivy.model.BaseException;
import com.ivy.dao.DynastyMapper;
import com.ivy.model.po.Dynasty;
import com.ivy.model.vo.DynastyVO;
import com.ivy.service.DynastyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */

@Service
public class DynastyServiceImpl implements DynastyService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private DynastyMapper dynastyMapper;


    @Override
    public Integer save(Dynasty dynasty) throws BaseException {
        Integer id = null;
        try {
            String name = dynasty.getName();
            Dynasty checkDynastry = dynastyMapper.selectByDynastyName(name);
            if (checkDynastry!=null){
                id = checkDynastry.getId();
                return  id;
            }
            dynastyMapper.insertSelective(dynasty);

            Dynasty dynasty1 = dynastyMapper.selectByDynastyName(name);
            if (dynasty1 != null ){
                id = dynasty1.getId();
            }
        } catch (Exception e) {
            LOG.error("【DynastyServiceImpl.save】",e);
        }
        return id;
    }

    @Override
    public Dynasty get(String name) throws BaseException {
        try {
            return  dynastyMapper.selectByDynastyName(name);
        } catch (Exception e) {
            LOG.error("【DynastyServiceImpl.get】",e);
        }
        return null;
    }

    @Override
    public List<DynastyVO> getAll() throws BaseException {
        try {
            return  dynastyMapper.selectAll();
        } catch (Exception e) {
            LOG.error("【DynastyServiceImpl.getAll】",e);
        }
        return null;
    }
}
