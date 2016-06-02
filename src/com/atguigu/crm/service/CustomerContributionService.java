package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.mapper.ContributionMapper;
import com.atguigu.crm.orm.Page;

@Service
public class CustomerContributionService {

	@Autowired
	private ContributionMapper contributionMapper;

	@Transactional
	public Page<Customer> getPage(int pageNo, Map<String, Object> mapperMap) {
		Page<Customer> page = new Page<>();
		page.setPageNo(pageNo);
		//获取总记录数
		int totalRecord = (int) contributionMapper.getTotalRecord(mapperMap);
		page.setTotalElements(totalRecord);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		mapperMap.put("firstIndex", firstIndex);
		mapperMap.put("endIndex", lastIndex);
		//获取当前页面的集合
		List<Customer> chances = contributionMapper.getContent(mapperMap);
		page.setContent(chances);
		return page;
	}
}
