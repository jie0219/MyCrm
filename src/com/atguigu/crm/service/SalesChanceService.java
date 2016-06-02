package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Transactional
	public void update(SalesChance chance){
		salesChanceMapper.update(chance);
	}
	
	@Transactional(readOnly=true)
	public SalesChance getById(Integer id){
		return salesChanceMapper.getById(id);
	}
	
	@Transactional
	public void save(SalesChance chance){
		chance.setStatus(1);
		salesChanceMapper.save(chance);
	}
	
	@Transactional
	public void delete(Integer id){
		salesChanceMapper.delete(id);
	}
	
	@Transactional
	public Page<SalesChance> getPage2(int pageNo, Map<String, Object> requestParams) throws ParseException {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		//获取总记录数
		int totalRecord = (int) salesChanceMapper.getTotalElements2(requestParams);
		page.setTotalElements(totalRecord);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		requestParams.put("firstIndex", firstIndex);
		requestParams.put("endIndex", lastIndex);
		//获取当前页面的集合
		List<SalesChance> chances = salesChanceMapper.getContent2(requestParams);
		page.setContent(chances);
		return page;
	}
	
	

	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo){
//		创建 Page 对象
		Page<SalesChance> page = new Page<>();
		
//		设置 pageNo 属性. 同时校验 pageNo 的合法性: 校验其是否大于 0
		page.setPageNo(pageNo);
		
//		获取总的记录数. 校验 pageNo 的合法性: 此时已经可以由 总的记录数和 pageSize 计算出总页数, 进而校验 pageNo 是否在合法的区间
		int totalElements = (int) salesChanceMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
//		查询当前页面的 content
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		List<SalesChance> content = salesChanceMapper.getContent(params);
		
//		为 Page 对象的 content 赋值
		page.setContent(content);
		
//		返回 Page 对象
		return page;
	}

	@Transactional
	public void saveDesignee( Integer id,SalesChance chance) {
		salesChanceMapper.updateChance( id,chance);
	}


	@Transactional
	public Page<SalesChance> getPlanPage2(int pageNo, Map<String, Object> mapperMap) {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		//获取总记录数
		int totalRecord = (int) salesChanceMapper.getTotalElements4(mapperMap);
		page.setTotalElements(totalRecord);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize() + 1 ;
		int lastIndex =firstIndex + page.getPageSize() ;
		mapperMap.put("firstIndex", firstIndex);
		mapperMap.put("endIndex", lastIndex);
		//获取当前页面的集合
		List<SalesChance> chances = salesChanceMapper.getContent4(mapperMap);
		page.setContent(chances);
		return page;
	}

	@Transactional
	public void stopChance(Integer id) {
		salesChanceMapper.stopChance(id);
	}

	@Transactional
	public void finishChance(SalesChance chance) {
		salesChanceMapper.finishChance(chance);
		String string = UUID.randomUUID().toString();
		String status = "正常";
		salesChanceMapper.insertCustomers(chance,string,status);
		long id = salesChanceMapper.selectId(string);
		salesChanceMapper.insertContacts(chance, id);
	}
}
