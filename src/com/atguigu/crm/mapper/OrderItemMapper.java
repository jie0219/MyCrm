package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.OrderItem;

public interface OrderItemMapper {

	List<OrderItem> getAllItem(@Param("id")Integer orderId);

}
