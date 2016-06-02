package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Customer;

public interface ContributionMapper {

	long getTotalRecord(Map<String, Object> mapperMap);

	List<Customer> getContent(Map<String, Object> mapperMap);

}
