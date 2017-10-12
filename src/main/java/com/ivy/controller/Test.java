package com.ivy.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ivy.dao.PoetriesMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ivy.dao.UsersMapper;
import com.ivy.model.po.Users;
import com.ivy.model.bo.PoetryBO;

//@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
//@ContextConfiguration(locations = {"classpath:spring-mvc-servlet.xml"})  
  
public class Test {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	/**
     * @param args
	 * @throws Exception 
     */
	
	@Autowired
    PoetriesMapper poetriesMapper;
	
	@Autowired
	static UsersMapper usersDao;
	
//	@org.junit.Test
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx=null;
        ctx=new ClassPathXmlApplicationContext("spring-mvc-servlet.xml");
        PoetriesMapper poetryDao= (PoetriesMapper) ctx.getBean("poetryDao");
        UsersMapper userDao= (UsersMapper) ctx.getBean("userDao");
//        User user=new User();
        //添加两条数据
//        user.setId("1");
//        user.setNickname("Jessica");
//        user.setPassword("123");
//        user.setPhone("15601719880");
//        user.setStatus(0);
//        user.setActive(0);
//        userDao.insertSelective(user);
//        System.out.println("添加成功");
        //查询数据
//        User user=new User();
//        user.setPhone("15601719880");
//        user.setPassword("123");
//        System.out.println(userDao.selectUserByPhonePassword("15601719880","123"));
        
        String selectUser = "select * from User where phone = '15601719880' and password = '123' and status = 1 and active = 1";
        Map<String,String> map = new HashMap<String,String>();
        map.put("phone", "15601719880");
        map.put("password", "123");
        Users user = usersDao.selectUserByParams(map);
        System.out.println(user.getNickname()+"  "+user.getPhone());
    }
}
