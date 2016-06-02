package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Order;

public interface OrderMapper {

	long getTotalElements(@Param("id")Integer id);

	List<Order> getOrderList(Map<String, Object> params);

	Order getById(@Param("id") Integer orderId);

}
