package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.mapper.OrderItemMapper;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemMapper itemMapper;

	public List<OrderItem> getAllItem(Integer orderId) {
		return itemMapper.getAllItem(orderId);
	}
}
