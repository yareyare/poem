package com.ivy.serviceImpl;

import com.ivy.daoImpl.ReConnectMapperImpl;
import com.ivy.service.ReConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class ReConnectServiceImpl implements ReConnectService {

    @Autowired
    ReConnectMapperImpl reConnectMapperImpl;

	public int reConnection() throws Exception {
		return reConnectMapperImpl.reConnection();
	}
    
}
