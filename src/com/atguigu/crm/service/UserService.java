package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyParseUtils;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name, String password){
		User user = userMapper.getByName(name);
		
		if(user != null
				&& password.equals(user.getPassword())
				&& user.getEnabled() == 1){
			return user;
		}
		
		return null;
	}
	@Transactional
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
	
	
	@Transactional
	public Page<User> getPage(int pageNo, Map<String, Object> params) throws ParseException {
		Page<User> page = new Page<>();
		page.setPageNo(pageNo);

		params = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int totalRecord = (int)userMapper.getTotalElements(params);
		
		page.setTotalElements(totalRecord);
		
		int firstIndex = (pageNo-1)*3 +1;
		int endIndex = firstIndex+3;
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<User> content =  userMapper.getlist(params);
		page.setContent(content);
		return page;
	}
	@Transactional
	public void saveUser(User user) {
		userMapper.save(user);
	}
	@Transactional
	public void updateEnabled(Integer id) {
		userMapper.updateEnabled(id);
		
	}
	public User getById(Integer id) {
		return userMapper.getById(id);
	}
	public void update(User user) {
		userMapper.update(user);
	}
	public User getUserByUsername(String username) {
		return userMapper.getByUsername(username);
	}
}
