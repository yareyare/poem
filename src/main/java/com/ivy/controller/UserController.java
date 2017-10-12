package com.ivy.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivy.controller.valid.UsersValid;
import com.ivy.model.po.Session;
import com.ivy.model.po.Users;
import com.ivy.model.bo.UsersLoginBO;
import com.ivy.service.SessionService;
import com.ivy.service.UsersService;
import com.ivy.tool.Code;
import com.ivy.tool.JsonConvert;
import com.ivy.tool.Return;

@RestController
@RequestMapping(value = "user")
public class UserController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	UsersService usersService;

	@Autowired
	SessionService sessionService;

	/**
	 * 根据用户登录账户检查账号是否存在，然后检查session是否有效，最后登出更改session状态
	 * @param params 
	 * 包含两个参数String accessToken,String param
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Return loginOut(@RequestParam Map<String, Object> params) {
		try {
			if (params == null 
					|| params.get("accessToken") == null || "".equals(params.get("accessToken"))
					|| params.get("param") == null || "".equals(params.get("param"))) {
				return Return.FAIL(Code.PARAM_NULL);
			}

			// 检查账户是否存在
			Users user = usersService.getUserByParam((String)params.get("param"));
			if (user == null) {
				return Return.FAIL(Code.USER_NOT_FIND);
			}

			Session session = sessionService.getSessionById((Integer)params.get("accessToken"));

			if (session == null) {
				return Return.FAIL(Code.NO_SESSION);
			}

			if (!session.getUserId().equals(user.getId())) {
				return Return.FAIL(Code.LOGIN_OUT_FAIL);
			}

			session.setStatus(0);

			if (sessionService.updateSession(session) == 1) {
				return Return.SUCCESS(Code.SUCCESS).put("data", null);
			}
		} catch (Exception e) {
			logger.error("登出失败", e);
		}
		return Return.FAIL(Code.LOGIN_OUT_FAIL);
		
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Return regist(@RequestBody Users user) {
		Code code = UsersValid.addValid(user);
		if (code != null) {
			return Return.FAIL(code);
		}
		try {
			int ret = usersService.addUser(user);
			if (ret != 1) {
				logger.error("【UserController】【addUser】添加用户返回值不是1");
			} else {
				return Return.SUCCESS(Code.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("【UserController】【addUser】添加用户异常", e);
		}
		return Return.FAIL(Code.FAILT);
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public Return modifyUser(@RequestBody Users user) {
		Code code = UsersValid.modifyValid(user);
		if (code != null) {
			return Return.FAIL(code);
		}
		//校验用户名密码是否正确
		
		try {
			Users u = usersService.getUserByParam(user.getEmail(), user.getPassword());
			if(u==null){
				return Return.FAIL(Code.USER_NOT_EXIST.code, Code.USER_NOT_EXIST.note);
			}
			int ret = usersService.modifyUser(user);
			if (ret != 1) {
				logger.error("【UserController】【addUser】修改用户返回值不是1");
			} else {
				return Return.SUCCESS(Code.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("【UserController】【addUser】修改用户异常", e);
		}
		return Return.FAIL(Code.FAILT);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Return login(@RequestBody UsersLoginBO userVO) {
		Code code = UsersValid.loginValid(userVO);
		if (code != null) {
			return Return.FAIL(code);
		}
		try {
			//param 用户名，身份证，或者昵称
			Users user = usersService.getUserByParam(userVO.getParam(), userVO.getPassword());
			if (user == null) {
				logger.error("【UserController】【addUser】getUserByParam 获取失败" + JsonConvert.toJson(userVO));
			} 
			// 加入session
            Session session = new Session();
            session.setUserId(user.getId());
            sessionService.invalidOldSession(user.getId());//历史session失效
            Integer acc_token = sessionService.insertAndGetId(session);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("email", user.getEmail());
            map.put("phone", user.getPhone());
            map.put("accessToken", acc_token);
            map.put("userId", user.getId());
            map.put("username", user.getNickname());
            return Return.SUCCESS(Code.SUCCESS.code, Code.SUCCESS.name()).put("data",map);
		} catch (Exception e) {
			logger.error("【UserController】【addUser】查询用户异常" + JsonConvert.toJson(userVO), e);
		}
		return Return.FAIL(Code.FAILT);
	}
}
