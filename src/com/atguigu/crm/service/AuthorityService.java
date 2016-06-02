package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.mapper.AuthorityMapper;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityMapper authorityMapper;

	public List<Authority> getParentAuth() {
		return authorityMapper.getParentAuth();
	}

	public void insert(Integer authId,  Integer id) {
		authorityMapper.insert(authId,id);
	}

	public void delete(Integer id) {
		authorityMapper.delete(id);
	}
}
