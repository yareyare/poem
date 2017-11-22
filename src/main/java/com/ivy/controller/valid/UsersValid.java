package com.ivy.controller.valid;

import com.ivy.model.po.User;
import com.ivy.model.bo.UsersLoginBO;
import com.ivy.tool.Code;
import com.ivy.tool.MatchEmail;
import com.ivy.tool.MatchIC;
import com.ivy.tool.MatchPhone;

public class UsersValid {
	public static Code addValid(User user){
		if(user==null){
			return Code.OBJECT_NULL;
		}
		if(user.getPhone() == null || "".equals(user.getPhone())){
			return Code.PARAM_NULL;
		}else{
			if(!MatchPhone.match(user.getPhone())){
				return Code.PARAM_MATCH_ERROR;
			}
		}
		if(user.getEmail()== null || "".equals(user.getEmail())){
			return Code.PARAM_NULL;
		}else{
			if(!MatchEmail.match(user.getEmail())){
				return Code.PARAM_MATCH_ERROR;
			}
		}
		if(user.getNickname()== null || "".equals(user.getNickname())){
			return Code.PARAM_NULL;
		}
		if(user.getPassword()== null || "".equals(user.getPassword())){
			return Code.PARAM_NULL;
		}
		return null;
	}
	
	public static Code modifyValid(User user){
		if(user==null){
			return Code.OBJECT_NULL;
		}
		if(user.getId() == null || "".equals(user.getId())){
			return Code.PARAM_NULL;
		}
		if(user.getPhone() == null || "".equals(user.getPhone())){
			return Code.PARAM_NULL;
		}else{
			if(!MatchPhone.match(user.getPhone())){
				return Code.PARAM_MATCH_ERROR;
			}
		}
		if(user.getEmail()== null || "".equals(user.getEmail())){
			return Code.PARAM_NULL;
		}else{
			if(!MatchEmail.match(user.getEmail())){
				return Code.PARAM_MATCH_ERROR;
			}
		}
		if(user.getNickname()== null || "".equals(user.getNickname())){
			return Code.PARAM_NULL;
		}
		if(user.getPassword()== null || "".equals(user.getPassword())){
			return Code.PARAM_NULL;
		}
		if(user.getIc() != null && !"".equals(user.getIc()) && !"".equals(MatchIC.IDCardValidate(user.getIc()))){
			return Code.PARAM_MATCH_ERROR;
		}
		return null;
	}
	
	// 手机 邮箱 昵称 都可以登录
	public static Code loginValid(UsersLoginBO userVO){
		// 手机 邮箱 昵称 都可以登录
		if(userVO==null){
			return Code.OBJECT_NULL;
		}
		if(userVO.getParam() == null || "".equals(userVO.getParam())){
			return Code.PARAM_NULL;
		}
		if(userVO.getPassword() == null || "".equals(userVO.getPassword())){
			return Code.PARAM_NULL;
		}
		return null;
	}
}
