package com.atguigu.crm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.service.SalesChanceService;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ApplicationContextTest {

	private ApplicationContext ctx = null;
	private SalesChanceService salesChanceService = null;
	private SalesChanceMapper salesChanceMapper = null;
	UserMapper userMapper = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		salesChanceService = ctx.getBean(SalesChanceService.class);
		salesChanceMapper = ctx.getBean(SalesChanceMapper.class);
		 userMapper = ctx.getBean(UserMapper.class);
	}
	
	@Test
	public void testParamType() throws ParseException{
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", "1");
		
		List<SalesChance> chances = salesChanceMapper.test(params);
		System.out.println(chances.size());*/
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("e2b87e6eced06509");;
		System.out.println(salt);
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		
	}
	
	
	

}
