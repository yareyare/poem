package com.ivy.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.UsersMapper;
import com.ivy.model.po.Users;
import com.ivy.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersMapper usersMapper;
	
	public int addUser(Users users) throws Exception {
		return usersMapper.insertSelective(users);
	}

	public Users getUserById(Integer id) throws Exception {
		return usersMapper.selectByPrimaryKey(id);
	}

	public int modifyUser(Users user) throws Exception {
		return usersMapper.updateByPrimaryKeySelective(user);
	}

	public Users getUserByParam(String param, String password) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("param", param);
		map.put("password", password);
		return usersMapper.selectUserByParamsPassword(map);
	}

	public Users getUserByParam(String param) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("param", param);
		return usersMapper.selectUserByParams(map);
	}
	
	
}
