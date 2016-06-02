package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerDrainService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.utils.MyParseUtils;

@Controller
@RequestMapping("/drain")
public class CustomerDrainHandler {

	@Autowired
	private CustomerDrainService customerDrainService;
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value="/delay",method=RequestMethod.PUT)
	public String save(@RequestParam("id")Integer id,
						@RequestParam("delay") String delay){
		
		
		return "1";
	}

	
	@RequestMapping("/delay")
	public String delay(@RequestParam("id")Integer id, Map<String, Object> map) {
		CustomerDrain drain = customerDrainService.getById(id);
		map.put("drain", drain);
		return "drain/input";
	}
	
	@RequestMapping("/confirm")
	public String confirm(CustomerDrain drain,
						@RequestParam("customerId") Integer custId
					,Map<String, Object> map,RedirectAttributes ra) {
		drain.setDrainDate(new Date());
		drain.setStatus("流失");
		customerDrainService.confirm(custId,drain);
		ra.addFlashAttribute("message","流失了");
		return "redirect:/drain/list2";
	}
	
	
	@RequestMapping("/list2")
	public String list(HttpServletRequest request, 
				@RequestParam(value = "pageNo", required = false) String pageNoStr,
				Map<String, Object> map) throws ParseException{
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String requestParamStr = MyParseUtils.parseRequestParams2QueryString(params);
		// 解析成Mapper需要的map 类型参数
		// 把key 从"Like_XX" 改成"XX" 把value 转换为"%XX%"
		Map<String, Object> mapperMap = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, mapperMap);
		map.put("page", page);
		map.put("queryString", requestParamStr);
		
		
		return "drain/list";
	}
	
	@RequestMapping("/analyse/list2")
	public String analyse(HttpServletRequest request, 
				@RequestParam(value = "pageNo", required = false) String pageNoStr,
				Map<String, Object> map) throws ParseException{
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String requestParamStr = MyParseUtils.parseRequestParams2QueryString(params);
		// 解析成Mapper需要的map 类型参数
		// 把key 从"Like_XX" 改成"XX" 把value 转换为"%XX%"
		Map<String, Object> mapperMap = MyParseUtils.parseRequestParams2MyBatisParams(params);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		
		Page<CustomerDrain> page = customerDrainService.getPage2(pageNo, mapperMap);
		map.put("page", page);
		map.put("queryString", requestParamStr);
		return "drain/analyse";
		
		/*map.put("java", 50);
		map.put("php", 50);
		return "myChartView";*/
	}
	
}
