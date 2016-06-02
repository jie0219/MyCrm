package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional(readOnly=true)
	public Page<Contact> getPage(int pageNo,int id){
		Page<Contact> page=new Page<>();
		page.setPageNo(pageNo);
		int totalRecord = (int) contactMapper.getTotalRecord(id);
		page.setTotalElements(totalRecord);
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		Map<String, Object> mapperMap=new HashMap<>();
		mapperMap.put("firstIndex", firstIndex);
		mapperMap.put("endIndex", lastIndex);
		mapperMap.put("id",id);
		List<Contact> content = contactMapper.getContent(mapperMap);
		page.setContent(content);
		return page;
	}
}
