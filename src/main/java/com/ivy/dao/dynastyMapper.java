package com.ivy.dao;

import com.ivy.model.po.dynasty;

public interface dynastyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(dynasty record);

    int insertSelective(dynasty record);

    dynasty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(dynasty record);

    int updateByPrimaryKey(dynasty record);
}