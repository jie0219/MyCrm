package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerActivity;

public interface CustomerActivityMapper {

	List<CustomerActivity> getActivityList(Map<String, Object> map);

	long getTotalElements(@Param("id")Integer id);

	void save(CustomerActivity activity);

	void delete(@Param("id") Integer id);

	CustomerActivity getById(@Param("id") Integer id);

	void update(CustomerActivity activity);

}
