package com.ivy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.PoetriesMapper;
import com.ivy.model.bo.PoetriesIndexBO;
import com.ivy.service.PoetriesService;

@Service
public class PoetryServiceImpl implements PoetriesService {

	@Autowired
	PoetriesMapper poetriesMapper;

	public List<PoetriesIndexBO> getIndexPoetries() throws Exception {
		return poetriesMapper.getIndexPoetries();
	}
	
	//private final String sql = "select title,content,auth.name as author from poetries p,poets auth where p.poet_id=auth.id order by rand() limit 4";
//	public List<PoetryVo> getIndexPoetries() throws Exception {
//		List<PoetryVo> list = poetryDao.getIndexPoetry(sql);
//		return list;
//	}

	
}
