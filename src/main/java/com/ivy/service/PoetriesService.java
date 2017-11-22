package com.ivy.service;

import com.ivy.model.bo.PoetriesIndexBO;

import java.util.List;

public interface PoetriesService {
	
	List<PoetriesIndexBO> getIndexPoetries() throws Exception;
	
}
