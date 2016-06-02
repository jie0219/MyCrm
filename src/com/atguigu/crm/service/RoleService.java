package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.RoleMapper;
import com.atguigu.crm.orm.Page;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getAllRole() {
		
		return roleMapper.getAllRole();
	}

	@Transactional
	public Page<Role> getPage(int pageNo) {
		Page<Role> page = new Page<>();
		page.setPageNo(pageNo);

		int totalRecord = (int)roleMapper.getTotalElements();
		
		page.setTotalElements(totalRecord);
		
		int firstIndex = (pageNo-1)*3 +1;
		int endIndex = firstIndex+3;
		Map<String, Object> params  = new HashMap<String, Object>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<Role> content =  roleMapper.getRoleList(params);
		page.setContent(content);
		return page;
	}

	public Role getById(Integer id) {
		return roleMapper.getById(id);
	}
	
}
