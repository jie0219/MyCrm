package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.atguigu.crm.entity.CustomerDrain;


public interface CustomerDrainMapper {
	
	@Update("{call check_drain}")
	void doCustomerDrain();

	long getTotalRecord(Map<String, Object> mapperMap);

	List<CustomerDrain> getContent(Map<String, Object> mapperMap);

	CustomerDrain getById(@Param("id")Integer id);

	void confirm(CustomerDrain drain);

	long getTotalRecord2(Map<String, Object> mapperMap);
	List<CustomerDrain> getContent2(Map<String, Object> mapperMap);
}
