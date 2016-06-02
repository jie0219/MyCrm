package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.utils.MyParseUtils;

@Controller
@RequestMapping("/customer")
public class CustomerHandler {
	@Autowired
	private CustomerService customerService;
	/*
	 * 查询客户类表
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(Customer customer,Map<String,Object>map,RedirectAttributes attributes) {
		customerService.update(customer);
		attributes.addFlashAttribute("message", "修改成功");
		return "redirect:/customer/list";
	}
	
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String,Object>map) {
		
		Customer customer=customerService.getById(id);
		map.put("customer", customer);
		// 地区列表
		map.put("regions", customerService.getModelList("地区"));
					// 放入客户经理
		map.put("managers", customerService.getManagerList(id));
					// 等级列表
		map.put("levels", customerService.getModelList("客户等级"));
					// 满意度列表
		map.put("satisfies", customerService.getModelList("满意度"));
					// 信用度列表
		map.put("credits", customerService.getModelList("信用度"));
		return "/customer/input";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageStr ,Map<String,Object>map) {
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageStr);
		} catch (NumberFormatException e) {
		}
		
		Page<Customer> page=customerService.getPage(pageNo);
		map.put("page", page);
		return "customer/list";
	}
	@RequestMapping(value="/list2")
	public String showCustomerList(@RequestParam(value="pageNo",required=false )String pageNoStr
					,Map<String,Object> map,HttpServletRequest request) throws Exception {
		int pageNo = 1 ;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		Page<Customer> page =  customerService.getCustomerList(pageNo,params);
		
		map.put("page", page);
		
		List<Dict> regionList =  customerService.getRegionList("地区");
		map.put("regionList", regionList);
		
		List<Dict> levelList  =  customerService.getRegionList("客户等级");
		map.put("levelList", levelList);
		
		String queryString = MyParseUtils.parseRequestParams2QueryString(params);
		map.put("queryString",queryString);
		
		return "customer/list";
	}
	//2.删除用户。改变用户状态
	@RequestMapping("/")
	@ResponseBody
	public String deleteCustomer(@RequestParam("id") Integer id){
		customerService.deleteCustomer(id);
		return "1";
	}
	
	@RequestMapping("/consist/list2")
	public String toConsist(Map<String,Object> map){
		
		return  "customer/consist";
	}

	@RequestMapping("/consist")
	public String satify(@RequestParam(value="type",required=false) String type,Map<String,Object> map){
		if(type==null){
			List<Dict> list =customerService.getLevel();
			map.put("title", "客户等级");
			for (Dict dict : list) {
				map.put(dict.getItem(), dict.getCount());
			}
			return  "myChartView";
		}
		
		if ("satify".equals(type)) {
		List<Dict> list =customerService.getSatify();
		map.put("title", "客户满意度");
		for (Dict dict : list) {
			map.put(dict.getItem(), dict.getCount());
			}
		}
		
		if("credit".equals(type)){
			List<Dict> list =customerService.getCredit();
			map.put("title", "客户信用度");
			for (Dict dict : list) {
				map.put(dict.getItem(), dict.getCount());
			}
		}
		return  "myChartView";
	}
	
}
