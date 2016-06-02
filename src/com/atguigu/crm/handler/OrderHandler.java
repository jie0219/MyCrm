package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.OrderItemService;
import com.atguigu.crm.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderHandler {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	@RequestMapping("/details/{id}")
	public String details(@PathVariable("id")Integer orderId, 
				Map<String, Object> map){
		
		Order order =orderService.getById(orderId);
		map.put("order", order);
		
		List<OrderItem> orderItem = orderItemService.getAllItem(orderId);
		map.put("itemList", orderItem);
		return "order/details";
	}
	
	@RequestMapping("/list2")
	public String toOrderList(@RequestParam("customerId")Integer id,
								@RequestParam(value="pageNo",required=false) String pageNoStr,
								Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<Order> page = orderService.getPage(id,pageNo);
		map.put("page", page);
		map.put("customerId", id);
		return"order/orderList";
	}
}
