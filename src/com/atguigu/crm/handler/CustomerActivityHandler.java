package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerActivityService;
import com.atguigu.crm.service.CustomerService;

@Controller
@RequestMapping("/activity")
public class CustomerActivityHandler {

	@Autowired
	private CustomerActivityService activiService;
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 编辑交往记录
	 */
	@RequestMapping(value = "/create",method = RequestMethod.PUT)
	public String edit(CustomerActivity activity){
		System.out.println(activity.toString());
		System.out.println(activity.getCustomer().getId());
		activiService.update(activity);
		return "redirect:/activity/list2/"+activity.getCustomer().getId();
	}
	
	
	/**
	 * 到编辑页面
	 */
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id")Integer id,
						Map<String, Object> map,
						@RequestParam("customer")Integer cusId ){
		CustomerActivity ca = activiService.getById(id);
		map.put("activity", ca);
		map.put("id", cusId);
		return "activity/create";
	}
	
	/**
	 * 删除交往记录
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id,
							@RequestParam("customer")Integer cusId ){
		
		activiService.delete(id);
		return "redirect:/activity/list2/"+cusId;
	}
	
	/**
	 * 新建交往记录
	 */
	@RequestMapping("/create")
	public String create(CustomerActivity activity){
		
		activiService.save(activity);
		return "redirect:/activity/list2/"+activity.getCustomer().getId();
	}
	
	
	@RequestMapping("/create/{id}")
	public String toCreatePage(@PathVariable("id")Integer id,
				Map<String, Object> map){
		
		map.put("activity",new CustomerActivity());
		map.put("id", id);
		
		return "activity/create";
	}
	
	/**
	 * 显示当前客户的交往记录
	 * @return
	 */
	@RequestMapping("/list2")
	public String toActivityPage(@RequestParam("customerId")Integer customerId,
							@RequestParam(value="pageNo",required=false)String pageNoStr,
								Map<String, Object> map){
		
		int pageNo = 1;
		System.out.println(customerId);
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<CustomerActivity> page = activiService.getPage(customerId,pageNo);
		map.put("page", page);
		Customer customer = customerService.getById(customerId);
		map.put("customer", customer);
		return "activity/activity";
	}
}
