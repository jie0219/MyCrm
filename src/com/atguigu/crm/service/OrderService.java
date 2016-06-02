package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.entity.Order;
import com.atguigu.crm.mapper.OrderMapper;
import com.atguigu.crm.orm.Page;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Transactional
	public Page<Order> getPage(Integer id, int pageNo) {
		Page<Order> page = new Page<>();
		page.setPageNo(pageNo);
		int totalElements = (int) orderMapper.getTotalElements(id);
		page.setTotalElements(totalElements);
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		params.put("id", id);
		List<Order> content = orderMapper.getOrderList(params);
		// 为 Page 对象的 content 赋值
		page.setContent(content);
		// 返回 Page 对象
		return page;
	
	}
	@Transactional
	public Order getById(Integer orderId) {
		return orderMapper.getById(orderId);
	}
}
