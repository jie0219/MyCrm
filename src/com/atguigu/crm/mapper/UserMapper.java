package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.entity.User;

public interface UserMapper {
	
	@Select("SELECT u.id, u.name, password, u.enabled, salt, r.name as \"role.name\""
		  + "FROM users u "
		  + "LEFT OUTER JOIN roles r "
		  + "ON u.role_id = r.id "
		  + "WHERE u.name = #{name}")
	User getByName(@Param("name") String name);

	List<User> getAllUser();

	long getTotalElements(Map<String, Object> params);

	List<User> getlist(Map<String, Object> params);

	void save(User user);

	void updateEnabled(@Param("id") Integer id);

	User getById(@Param("id") Integer id);

	void update(User user);

	User getByUsername(@Param("name")String username);
	
	
}
