package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.DictMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.utils.MyParseUtils;
/*
 * 哈哈啊哈
 */
@Service
public class DictService {
	@Autowired
	
	private DictMapper dictMapper;


	public Page<Dict> getPage(int pageNo) {
		Page<Dict> page = new Page<Dict>();
		page.setPageNo(pageNo);
		
		int totalElements=(int) dictMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
		int firstIndex=(pageNo-1)*page.getPageSize()+1;
		
		int enIndex=firstIndex+page.getPageSize()-1;
		
		Map<String, Object>params=new HashMap<>();
		
		params.put("firstIndex", firstIndex);
		params.put("endIndex", enIndex);
		
		List<Dict>content=dictMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}


	public void save(Dict dict) {
		dictMapper.save(dict);
		
	}


	public void deleteById(Integer id) {
		dictMapper.delete(id);
		
	}


	public Dict getById(Integer id) {
		return dictMapper.getById(id);
	}


	public void update(Dict dict) {
		dictMapper.update(dict);
		
	}


	public Page<Dict> getPage2(int pageNo, Map<String, Object> params) throws ParseException {
		Page<Dict> page = new Page<Dict>();
		page.setPageNo(pageNo);
		System.out.println(pageNo);
		Map<String, Object> myBatisParams = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int totalElements=(int) dictMapper.getTotalElements2(myBatisParams);
		page.setTotalElements(totalElements);
		int firstIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=firstIndex+page.getPageSize()-1;
		
		myBatisParams.put("firstIndex", firstIndex);
		myBatisParams.put("endIndex", endIndex);
		List<Dict>content=dictMapper.getContent2(myBatisParams);
		page.setContent(content);
		
		return page;
	}
	
}
