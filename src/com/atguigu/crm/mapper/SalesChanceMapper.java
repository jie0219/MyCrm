package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;

public interface SalesChanceMapper {

	List<SalesChance> test(Map<String, Object> params);
	
	List<SalesChance> getContent2(Map<String, Object> params);
	
	long getTotalElements2(Map<String, Object> params);
	
	
	void update(SalesChance chance);
	
	SalesChance getById(@Param("id") Integer id);
	
	void save(SalesChance chance);
	
	void delete(@Param("id") Integer id);
	
	List<SalesChance> getContent(Map<String, Object> params);
	
	long getTotalElements();

	void updateChance(@Param("id") Integer id,@Param("chance") SalesChance chance);

	

	long getTotalElements4(Map<String, Object> mapperMap);

	List<SalesChance> getContent4(Map<String, Object> mapperMap);

	void stopChance(@Param("id")Integer id);

	void finishChance(SalesChance chance);

	long insertCustomers(@Param("chance") SalesChance chance,@Param("no") String string, @Param("state")String status);

	void insertContacts(@Param("chance") SalesChance chance, @Param("customerId")long id);

	long selectId(@Param("no") String string);




	
}
