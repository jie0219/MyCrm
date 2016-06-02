package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.CustomerDrainMapper;
import com.atguigu.crm.orm.Page;

@Service
public class CustomerDrainService {

	@Autowired
	private CustomerDrainMapper drainMapper;
	
	@Autowired
	private CustomerService customerService;
	
	@Transactional
	public void toDoCustomerDrain(){
		
		drainMapper.doCustomerDrain();
	}
	@Transactional
	public Page<CustomerDrain> getPage(int pageNo, Map<String, Object> mapperMap) {
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		//获取总记录数
		int totalRecord = (int) drainMapper.getTotalRecord(mapperMap);
		page.setTotalElements(totalRecord);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		mapperMap.put("firstIndex", firstIndex);
		mapperMap.put("endIndex", lastIndex);
		//获取当前页面的集合
		List<CustomerDrain> chances = drainMapper.getContent(mapperMap);
		page.setContent(chances);
		return page;
	}
	@Transactional
	public CustomerDrain getById(Integer id) {
		return drainMapper.getById(id);
	}
	@Transactional
	public void confirm(Integer custId,CustomerDrain drain) {
		customerService.updateStatus(custId);
		drainMapper.confirm(drain);
	}
	
	@Transactional
	public Page<CustomerDrain> getPage2(int pageNo, Map<String, Object> mapperMap) {
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		//获取总记录数
		int totalRecord = (int) drainMapper.getTotalRecord2(mapperMap);
		page.setTotalElements(totalRecord);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		mapperMap.put("firstIndex", firstIndex);
		mapperMap.put("endIndex", lastIndex);
		//获取当前页面的集合
		List<CustomerDrain> chances = drainMapper.getContent2(mapperMap);
		page.setContent(chances);
		return page;
	}
}
