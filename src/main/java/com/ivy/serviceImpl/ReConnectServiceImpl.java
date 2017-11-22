package com.ivy.serviceImpl;

import com.ivy.dao.ReConnectMapper;
import com.ivy.service.ReConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class ReConnectServiceImpl implements ReConnectService {

    @Autowired
	private ReConnectMapper reConnectMapper;

	public int reConnection() throws Exception {
		return reConnectMapper.reConnection();
	}
    
}
