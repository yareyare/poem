package com.ivy.service;

import com.ivy.model.po.Users;

public interface UsersService {
	
	public int addUser(Users users) throws Exception;
	
	public Users getUserById(Integer id) throws Exception;
	
	public int modifyUser(Users user) throws Exception;
	
	public Users getUserByParam(String param ,String password) throws Exception;
	
	public Users getUserByParam(String param) throws Exception;
	
}
