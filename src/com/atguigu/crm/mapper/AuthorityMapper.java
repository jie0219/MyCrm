package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Authority;


public interface AuthorityMapper {

	List<Authority> getParentAuth();

	void insert(@Param("authId") Integer authId, @Param("id") Integer id);

	void delete(Integer id);

	
}
