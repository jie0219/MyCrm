package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.mapper.CustomerActivityMapper;
import com.atguigu.crm.orm.Page;

@Service
public class CustomerActivityService {

	@Autowired
	private CustomerActivityMapper activityMapper;

	@Transactional
	public Page<CustomerActivity> getPage(Integer id, int pageNo) {

		Page<CustomerActivity> page = new Page<>();
		page.setPageNo(pageNo);
		int totalElements = (int) activityMapper.getTotalElements(id);
		page.setTotalElements(totalElements);
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		params.put("id", id);
		List<CustomerActivity> content = activityMapper.getActivityList(params);
		// 为 Page 对象的 content 赋值
		page.setContent(content);
		// 返回 Page 对象
		return page;

	}

	@Transactional
	public void save(CustomerActivity activity) {
		activityMapper.save(activity);
	}

	@Transactional
	public void delete(Integer id) {
		activityMapper.delete(id);
	}

	@Transactional
	public CustomerActivity getById(Integer id) {
		return activityMapper.getById(id);
	}

	@Transactional
	public void update(CustomerActivity activity) {
		activityMapper.update(activity);
	}
}
