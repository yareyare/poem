package com.ivy.serviceImpl;

import com.ivy.model.BaseException;
import com.ivy.dao.PoemTypeMapper;
import com.ivy.model.po.PoemType;
import com.ivy.service.PoemTypeService;
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
public class PoemTypeServiceImpl implements PoemTypeService {

    private Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    private PoemTypeMapper poemTypeMapper;

    @Override
    public Integer save(String type1, String type2) throws BaseException {
        if (type1 == null || "".equals(type1)){
            throw new BaseException(Code.PARAM_NULL);
        }

        try {
            PoemType result = get(type1, type2);
            if (result != null){
                return result.getId();
            }
            PoemType poemType = new PoemType();
            poemType.setType(type1);
            poemType.setType1(type2);
            poemType.setCreateDate(new Date());
            int i = poemTypeMapper.insertSelective(poemType);
            if (i== 1){
                result = get(type1, type2);
                return result.getId();
            }
        } catch (BaseException e) {
            LOG.error("【PoemTypeServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
        return null;
    }


    @Override
    public Integer save(PoemType poemType) throws BaseException {
        if (poemType.getType() == null || "".equals(poemType.getType())){
            throw new BaseException(Code.PARAM_NULL);
        }
        try {
            int i = poemTypeMapper.insertSelective(poemType);
            if (i== 1){
                PoemType result = get(poemType.getType(), poemType.getType1());
                return result.getId();
            }
        } catch (BaseException e) {
            LOG.error("【PoemTypeServiceImpl.save】",e);
            throw new BaseException(Code.DB_ERROR);
        }
        return null;
    }

    @Override
    public PoemType get(String type1, String type2) throws BaseException {
        PoemType poemType = null;
        try {
            Map<String,String > param = new HashMap<>();
            param.put("type1",type1);
            param.put("type2",type2);
             poemType = poemTypeMapper.selectByTypeAndType1(param);
        } catch (Exception e) {
            LOG.error("根据大类小类查询类型失败",e);
            throw new BaseException(Code.DB_ERROR);
        }
        return poemType;
    }
}
