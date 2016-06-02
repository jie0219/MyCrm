package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	List<Role> getAllRole();

	long getTotalElements();

	List<Role> getRoleList(Map<String, Object> params);

	Role getById(@Param("id") Integer id);

}
