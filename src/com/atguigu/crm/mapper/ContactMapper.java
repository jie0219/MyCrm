package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	long getTotalRecord(Integer  id);
	
	List<Contact> getContent(Map<String, Object> map);
}
